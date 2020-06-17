package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス.
 * @author yu.konishi
 *
 */
@Service
@Transactional
public class AdministratorService {
	
	/**
	 * 管理者情報テーブルを操作するリポジトリ.
	 */
	@Autowired
	private AdministratorRepository administratorRepository;
	
}
