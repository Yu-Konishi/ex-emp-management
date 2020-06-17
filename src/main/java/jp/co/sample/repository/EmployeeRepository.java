package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * employeesを操作するリポジトリ.(DAO)
 * 
 * @author yu.konishi
 *
 */
@Repository
public class EmployeeRepository {

	/**
	 * Employeeオブジェクトを生成
	 */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getTimestamp("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};

	/**
	 * JDBCを操作するテンプレート
	 */
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 従業員一覧情報を入社日順で取得.
	 * 
	 * @return 該当した従業員情報のリスト(従業員が存在しない場合はサイズ0件の従業員一覧を返す)
	 */
	public List<Employee> findAll() {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,"
				+ "characteristics,dependents_count FROM employees ORDER BY hire_date DESC;";
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		return employeeList;
	}

	/**
	 * IDから従業員情報を取得.(従業員が存在しない場合は例外が発生)
	 * 
	 * @param id ID
	 * @return 該当する従業員情報
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,"
				+ "characteristics,dependents_count FROM employees WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}

	/**
	 * 従業員情報を変更.(idカラムは更新しない)
	 * 
	 * @param employee 従業員情報
	 */
	public void update(Employee employee) {
		String sql = "UPDATE employees SET name=:name,image=:image,gender=:gender,hire_date=:hireDate,"
				+ "mail_address=:mailAddress,zip_code=:zipCode,address=:address,telephone=:telephone,salary=:salary,"
				+ "characteristics=:characteristics,dependents_count=:dependendsCount WHERE id=:id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		template.update(sql, param);
	}

}
