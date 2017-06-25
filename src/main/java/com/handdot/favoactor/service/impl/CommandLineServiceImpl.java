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

import com.handdot.favoactor.service.ifs.CommandLineService;

/**
 * 主にnodejsの起動をしスクリプトを実行します。
 *
 * @author hand-dot
 *
 */
@Service
@Profile("production")
public class CommandLineServiceImpl implements CommandLineService{

	@Autowired
	ResourceLoader resourceLoader;

	final String filepath = "scripts/led.js";

	File tempFile;


	public void exceNodeLed() {
		try {
			if (tempFile == null) {
				tempFile = File.createTempFile("led", ".js");

				Resource resource = resourceLoader.getResource("classpath:" + filepath);
				FileUtils.copyInputStreamToFile(resource.getInputStream(), tempFile);
			}

			// Executor
			DefaultExecutor executor = new DefaultExecutor();

			// 非同期モード
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

			// 正常終了の場合に返される値
			executor.setExitValue(0);

			CommandLine commandLine = CommandLine.parse("node " + tempFile.getAbsolutePath());
			// コマンドの実行
			executor.execute(commandLine, resultHandler);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
