package com.handdot.favoactor.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.handdot.favoactor.service.ifs.ExecNodeJsService;

/**
 * 主にnodejsの起動をしスクリプトを実行します。
 *
 * @author hand-dot
 *
 */
@Service
@Profile("production")
public class ExecNodeJsImpl implements ExecNodeJsService {

	@Autowired
	ResourceLoader resourceLoader;

	File currentDir = new File(System.getProperty("user.dir"));

	// Executor
	DefaultExecutor executor = new DefaultExecutor();

	// 非同期モード
	DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

	// スクリプト実行コマンド
	CommandLine nodeLedJs = CommandLine.parse("node " + currentDir.getAbsolutePath() + "/relay.js");

	@Override
	public void setUp() {
		// 正常終了の場合に返される値
		executor.setExitValue(0);
		System.out.println("ExecNodeJsService#setUp Please wait...");
		try {
			// ②package.jsonをcurrentDirにコピーする
			File packageJson = new File(currentDir, "package.json");
			if (!packageJson.exists()) {
				FileUtils.copyInputStreamToFile(
						resourceLoader.getResource("classpath:scripts/package.json").getInputStream(), packageJson);
			}

			File scriptFile = new File(currentDir, "relay.js");
			// ③relay.jsonをカレントディレクトリにコピーする
			if (!scriptFile.exists()) {
				FileUtils.copyInputStreamToFile(
						resourceLoader.getResource("classpath:scripts/relay.js").getInputStream(), scriptFile);
			}
			// ④npm installを実行する
			File nodeModules = new File(currentDir, "node_modules");
			if (!nodeModules.exists()) {
				CommandLine npmInstall = CommandLine.parse("npm install");
				executor.execute(npmInstall);
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		System.out.println("ExecNodeJsService#setUp Done!!");
	}

	public void blinkLed() {
		try {
			// コマンドの実行
			executor.execute(nodeLedJs, resultHandler);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}