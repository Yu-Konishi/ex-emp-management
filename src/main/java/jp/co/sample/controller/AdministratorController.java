package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者登録画面の処理を行うコントローラ.
 * 
 * @author yu.konishi
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	/**
	 * 管理者情報を操作するサービス.
	 */
	@Autowired
	private AdministratorService administratorService;

	/**
	 * フォームの初期化
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	/**
	 * 管理者登録画面を表示.
	 * 
	 * @return 管理者登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	/**
	 * 管理者情報を登録.
	 * @param form フォーム
	 * @return　ログインページ
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		//フォームのデータをドメインにコピーする
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "/";
	}

}
