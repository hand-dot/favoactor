package com.handdot.favoactor.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
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
@Profile("development")
public class DummyExecNodeJsImpl implements ExecNodeJsService {

	@Autowired
	ResourceLoader resourceLoader;

	final String fileName = "dummy.js";

	File scriptsDir = new File(System.getProperty("java.io.tmpdir"), "favoactor_scripts");

	@Override
	public void setUp() {
		System.out.println("ExecNodeJsService#setUp Please wait...");
		try {
			if (scriptsDir.exists()) {
				return;
			}
			scriptsDir.mkdir();
			Resource resource = resourceLoader.getResource("classpath:scripts");
			FileUtils.copyDirectory(resource.getFile(), scriptsDir);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		System.out.println("ExecNodeJsService#setUp Done!!");
	}

	public void blinkLed() {
		try {
			// Executor
			DefaultExecutor executor = new DefaultExecutor();

			// 非同期モード
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

			// 正常終了の場合に返される値
			executor.setExitValue(0);

			CommandLine commandLine = CommandLine.parse("node " + scriptsDir.getAbsolutePath() + "/" + fileName);
			// コマンドの実行
			executor.execute(commandLine, resultHandler);
			//デバッグ用の待ち処理
			// resultHandler.waitFor();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}