package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import service.ArticleService;
import service.UserSerivce;
import cn.dsna.util.images.ValidateCode;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import entity.User;

@Controller
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private HttpServletRequest request;
	private HttpServletResponse response;

	UserAction(){
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
	}
	//ajax json数据结构
	private String jsonResult;

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	//model
	private User user = new User();

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public User getModel() {
		return user;
	}
	//地址字段处理
	private String[] addr;

	public String[] getAddr() {
		return addr;
	}

	public void setAddr(String[] addr) {
		this.addr = addr;
	}

	//文件上传
	private File userSculpture;//必须是File类型，名字对应表单的上传输入域<s:file/>中的name属性值，难点3（该File的变量名若在User类的某变量名相同，则会set User而不会set File）
	private String userSculptureFileName;//上传文件的文件名。XXXFileName固定写法
	private String userSculptureContentType;//上传文件的MIME类型。XXXContentType固定写法

	public File getUserSculpture() {
		return userSculpture;
	}

	public void setUserSculpture(File userSculpture) {
//		System.out.println("setUserSculpture");
		this.userSculpture = userSculpture;
	}

	public String getUserSculptureFileName() {
		return userSculptureFileName;
	}

	public void setUserSculptureFileName(String userSculptureFileName) {
//		System.out.println("setUserSculptureFileName");
		this.userSculptureFileName = userSculptureFileName;
	}

	public String getUserSculptureContentType() {
		return userSculptureContentType;
	}

	public void setUserSculptureContentType(String userSculptureContentType) {
//		System.out.println("setUserSculptureContentType");
		this.userSculptureContentType = userSculptureContentType;
	}
	//service
	private UserSerivce userService;

	public void setUserService(UserSerivce userService) {
		this.userService = userService;
	}
	private ArticleService articleService;


	public ArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	//a_id
	private int articleId;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	//跳转到修改页面
	public String editUI(){
		
		User temp = userService.findById(user.getId());
		user = temp;
		
		ValueStack vs = ActionContext.getContext().getValueStack();
		
		vs.set("pwd", temp.getPassword());
		
		Map<Integer,String> provinces = new HashMap<Integer, String>();
		provinces.put(1, "广东省");
		provinces.put(2, "山东省");
		vs.set("provinces", provinces);
		
		String addr = temp.getAddress();
		String[] addrs = addr.split("-");
		if(addrs.length>2){
			vs.set("prov", addrs[0]);
			vs.set("city", addrs[1]);
		}
		
		vs.set("formatBirthday", new SimpleDateFormat("yyyy-MM-dd").format(temp.getBirthday()));
		
		vs.push(temp);
		return "editUI";
	}
	//修改
	public String edit(){
		
		User temp = userService.findById(user.getId());
		if(!temp.getUsername().equals(user.getUsername())){
			temp.setUsername(user.getUsername());
		}
		if(!temp.getPassword().equals(user.getPassword())){
			temp.setPassword(user.getPassword());
		}
		if(!temp.getGender().equals(user.getGender())){
			temp.setGender(user.getGender());
		}
		if(temp.getBirthday().compareTo(user.getBirthday())!=0){
			temp.setBirthday(user.getBirthday());
		}
		if(!temp.getTelephone().equals(user.getTelephone())){
			temp.setTelephone(user.getTelephone());
		}
		if(!temp.getEmail().equals(user.getEmail())){
			temp.setEmail(user.getEmail());
		}
		if(!addr[0].equals("0")&&!addr[1].equals("0")){
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < addr.length; i++) {
				if(i>0){
					sb.append("-");
				}
				sb.append(addr[i]);
			}
			temp.setAddress(sb.toString());
		}
		if(userSculpture!=null){
			String realPath = "e:/bbs_file/user_sculpture";
			File outputFile = new File(realPath,userSculptureFileName);
			try {
				FileUtils.copyFile(userSculpture, outputFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			temp.setSculpture(realPath+"/"+userSculptureFileName);
		}
		request.getSession().setAttribute("user", temp);
		userService.update(temp);
		return "edit";
	}
	//跳转到注册页面
	public String registUI(){

		Map<Integer,String> provinces = new HashMap<Integer, String>();
		provinces.put(1, "广东省");
		provinces.put(2, "山东省");

		ActionContext.getContext().getValueStack().set("provinces", provinces);

		return "registUI";
	}
	//检查用户名是否已存在
	public String checkUsername(){

		User temp = userService.checkUsername(user.getUsername());
		Map<String,String> map = new HashMap<String, String>();
		if(temp!=null){
			map.put("check", "input again");
		}else{
			map.put("check", "pass");
		}
		//将map转成json对象（都是键值对）
		JSONObject jsonObject = JSONObject.fromObject(map);
		//最终回传的结果
		jsonResult = jsonObject.toString();
		return "jsonResult";
	}
	//二级联动
	public String city(){
		String id = request.getParameter("id");
		Map<String,String> cities = new HashMap<String, String>();
		if(id.equals("广东省")){
			cities.put("11", "深圳市");
			cities.put("12", "广州市");
			cities.put("13", "佛山市");
		}else if(id.equals("山东省")){
			cities.put("21", "济南市");
			cities.put("22", "青岛市");
			cities.put("23", "威海市");
		}
		JSONObject jsonObject = JSONObject.fromObject(cities);
		jsonResult = jsonObject.toString();
		return "jsonResult";
	}
	//检查日期、地址、头像输入
	public boolean checkRegist(){
		if(addr[0].equals("0")||addr[1].equals("0")){
			this.addActionMessage("请选择地址！");
		}
		if(user.getBirthday()==null){
			this.addActionMessage("请选择日期！");
		}
		if(userSculpture==null){
			this.addActionMessage("请上传头像！");
		}
		if(this.getActionMessages().size()>0){
			return false;
		}else{
			return true;
		}
	}
	//注册
	public String regist(){

		if(checkRegist()){
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < addr.length; i++) {
				if(i>0){
					sb.append("-");
				}
				sb.append(addr[i]);
			}
			user.setAddress(sb.toString());

			String realPath = "e:/bbs_file/user_sculpture";
			File outputFile = new File(realPath,userSculptureFileName);
			try {
				FileUtils.copyFile(userSculpture, outputFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			user.setSculpture(realPath+"/"+userSculptureFileName);
			//设置注册时间
			user.setRegist_time(new Date());
			//分配用户权限
			user.setPrivilege(1);
			//保存用户
			userService.save(user);

			return "login";
		}else{
			Map<Integer,String> provinces = new HashMap<Integer, String>();
			provinces.put(1, "广东省");
			provinces.put(2, "山东省");

			ActionContext.getContext().getValueStack().set("provinces", provinces);

			return "registUI";
		}

	}
	//ajax显示验证码
	public String showVC(){

		ValidateCode validateCode = new ValidateCode(150,60,4,10);
		validateCode.createCode();
		String code = validateCode.getCode();
		Map<String,String> map = new HashMap<String, String>();
		map.put("code", code);
		try {
			validateCode.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//		JSONObject jsonObject = JSONObject.fromObject(map);
		//		jsonResult = jsonObject.toString();
		//		return "jsonResult";
		request.getSession().setAttribute("code", code);
		return null;
	}
	//登录
	public String login(){
		String validateCode = request.getParameter("validateCode");
		if(validateCode.equalsIgnoreCase((String) request.getSession().getAttribute("code"))){
			User u = userService.login(user.getUsername(), user.getPassword());
			if(u==null){
				this.addActionMessage( "用户名或密码有误！");
				return "login";
			}else{
				HttpSession httpSession = ServletActionContext.getRequest().getSession();
				httpSession.setAttribute("user", u);
				return "homepage";
			}
		}else{
			this.addActionMessage( "验证码有误！");
			return "login";
		}

	}

	//注销
	public String logout() {
		//remove Session中的user字段
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return "homepage";
	}

	//显示图片
	public String showSculpture(){
		User u = userService.findById(user.getId());

		ActionContext.getContext().getValueStack().push(u);

		String fileLocation = u.getSculpture();
		InputStream in = null;
		try {
			in = new FileInputStream(new File(fileLocation));
			byte[] b = new byte[1024];
			int len = 0;
			while((len=in.read(b))!=-1){
				ServletActionContext.getResponse().getOutputStream().write(b, 0, len);
				ServletActionContext.getResponse().getOutputStream().flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	//加精
	public String setExc(){
		articleId = Integer.parseInt(ServletActionContext.getRequest().getParameter("id"));
		articleService.update(userService.setExc(articleId));
		return "setExc";
	}

}
