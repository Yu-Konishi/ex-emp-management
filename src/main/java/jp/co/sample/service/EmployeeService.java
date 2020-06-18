package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービス.
 * 
 * @author yu.konishi
 *
 */
@Service
@Transactional
public class EmployeeService {

	/**
	 * 従業員情報テーブルを操作するリポジトリ
	 */
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * 従業員情報を全取得をリポジトリに指示.
	 * 
	 * @return 全従業員情報
	 */
	public List<Employee> showList() {
		return employeeRepository.findAll();
	}

	/**
	 * IDに該当する従業員情報の取得を指示.
	 * 
	 * @param id ID
	 * @return 従業員情報
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}

	/**
	 * 従業員情報の更新を指示.
	 * 
	 * @param employee 従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}

}
