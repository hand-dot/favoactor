package com.handdot.favoactor.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.handdot.favoactor.service.ifs.CommandLineService;
import com.handdot.favoactor.util.FileUtil;

/**
 * 主にnodejsの起動をしスクリプトを実行します。
 *
 * @author hand-dot
 *
 */
@Service
@Profile("development")
public class DummyCommandLineServiceImpl implements CommandLineService {

	@Autowired
	ResourceLoader resourceLoader;

	final String fileName = "dummy.js";

	final String scriptsPath = "scripts/";

	public void exceNodeLed() {
		try {
			File tempDir = new File(System.getProperty("java.io.tmpdir"), "favoactor_development");

			Resource resource = resourceLoader.getResource("classpath:" + scriptsPath);

			// scriptsフォルダがない場合は作成する
			System.out.println(resource);
			System.out.println(resource.getFile());
			System.out.println(resource.getURI());
			System.out.println(resource.getURI().getPath());
			// https://stackoverflow.com/questions/39127662/java-load-folder-from-jar
			FileUtil.ifExistsCopyDir(resource.getFile(), tempDir);

			// Executor
			DefaultExecutor executor = new DefaultExecutor();

			// 非同期モード
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

			// 正常終了の場合に返される値
			executor.setExitValue(0);

			CommandLine commandLine = CommandLine.parse("node " + tempDir.getAbsolutePath() + "/" + fileName);
			// コマンドの実行
			executor.execute(commandLine, resultHandler);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}