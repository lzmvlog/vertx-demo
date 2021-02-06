package top.lzmvlog.vertx_demo.model;

import io.vertx.codegen.annotations.DataObject;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2021年01月16日 20:57
 * @Description:
 */
@DataObject
//@RowMapped
//@ParametersMapped
public class Student {

  /**
   * 学生id
   */
//    @Column(name = "id")
  private String id;

  /**
   * 学生名称
   */
//    @Column(name = "name")
  private String name;

  /**
   * 学生年龄
   */
  private Integer age;

  private Integer gradeId;

  public Student() {
  }

  public Student(String id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getGradeid() {
    return gradeId;
  }

  public void setGradeid(Integer gradeid) {
    this.gradeId = gradeid;
  }
}
