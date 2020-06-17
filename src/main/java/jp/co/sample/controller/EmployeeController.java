package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報関連を処理するコントローラ.
 * 
 * @author yu.konishi
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	/**
	 * 従業員情報サービス
	 */
	@Autowired
	private EmployeeService employeeService;

	/**
	 * 従業員一覧を表示.
	 * 
	 * @param model リクエストスコープ
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list.html";
	}

}
