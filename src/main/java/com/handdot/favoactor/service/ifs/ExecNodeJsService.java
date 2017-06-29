package com.handdot.favoactor.service.ifs;
/**
 * NodeJS実行サービス
 * @author hand-dot
 *
 */
public interface ExecNodeJsService {
	/**
	 * 実行に必要な準備をします。
	 */
	public void setUp();
	/**
	 * executorがスクリプトを実行します。
	 */
	public void execute();
}
