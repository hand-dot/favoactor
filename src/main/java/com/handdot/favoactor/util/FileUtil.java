package com.handdot.favoactor.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	/**
	 *	destDirが存在しない場合にsrcDirをコピーします。
	 *
	 * @param srcDir
	 * @param destDir
	 * @return 成功した場合、もしくは既に存在する場合trueを返す
	 */
	public static boolean ifExistsCopyDir(File srcDir, File destDir) {
		System.out.println(srcDir.getAbsolutePath());
		System.out.println(destDir.getAbsolutePath());
		if (!destDir.exists()) {
			boolean mkdirResult = false;
			try {
				mkdirResult = destDir.mkdir();
				if (!mkdirResult) {
					FileUtils.deleteDirectory(destDir);
				}
				FileUtils.copyDirectory(srcDir, destDir);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return mkdirResult;
		} else {
			return true;
		}
	}
}
