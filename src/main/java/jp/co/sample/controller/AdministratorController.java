package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者情報関連の処理を行うコントローラ.
 * 
 * @author yu.konishi
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	/**
	 * 管理者情報を操作するサービス
	 */
	@Autowired
	private AdministratorService administratorService;

	/**
	 * セッションスコープ
	 */
	@Autowired
	private HttpSession session;

	/**
	 * 管理者登録フォームの初期化.
	 * 
	 * @return 管理者登録フォーム
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	/**
	 * ログインフォームの初期化.
	 * 
	 * @return ログインフォーム
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	/**
	 * ログイン画面を表示.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
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
	 * 
	 * @param form フォーム
	 * @return ログインページ
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		// フォームのデータをドメインにコピーする
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";
	}

	/**
	 * ログイン機能.
	 * 
	 * @param form  フォーム
	 * @param model リクエストスコープ
	 * @return 従業員情報一覧ページ(ログインに失敗した場合はログインページ)
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		if (administrator == null) {
			model.addAttribute("error", "メールアドレスまたはパスワードが不正です。");
			return toLogin();
		}
		session.setAttribute("administratorName", administrator.getName());
		return "forward:/employee/showList";
	}

}
