package com.fh.controller.erp.company;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.util.AppUtil;
import com.fh.util.CodeRandomUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.service.erp.company.CompanyManager;
import com.fh.service.erp.companytype.CompanyTypeManager;
import com.fh.service.erp.customer.CustomerManager;
import com.fh.service.erp.level.LevelManager;
import com.fh.service.erp.remarks.RemarksManager;

/** 
 * 说明：客户管理
 * 创建人：liuyw	
 * 创建时间：2017-12-06
 */
@Controller
@RequestMapping(value="/company")
public class CompanyController extends BaseController {
	
	String menuUrl = "company/list.do"; //菜单地址(权限用)
	@Resource(name="companyService")
	private CompanyManager companyService;
	@Resource(name="companyTypeService")
	private CompanyTypeManager companyTypeService;
	@Resource(name="customerService")
	private CustomerManager customerService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Customer");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();		
		pd.put("CODE",CodeRandomUtil.getCompanyCode());		//编号
		pd.put("COMPANY_ID", this.get32UUID());		//主键
		pd.put("CTIME", Tools.date2Str(new Date()));	//建档时间
		pd.put("USERNAME", Jurisdiction.getUsername());	//用户名
		//累计金额初始化为0
		pd.put("MONEY", "0.0");	
		pd.put("INCOME", "0.0");	
		pd.put("OUTMONEY", "0.0");	
		pd.put("BILLFEE", "0.0");	
		pd.put("COUNTBILL", "0");
		companyService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Customer");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Page page = new Page();
		page.setPd(pd);
		
		List<PageData>	varList = customerService.listCompany(page);	//列出CustomerImg列表
		//当客户下有订单信息则不能删除
		if(varList.size()>0){
			errInfo = "false";
		}else{
			companyService.delete(pd);
		}
		map.put("result", errInfo);
				
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**随机抽取
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/random")
	@ResponseBody
	public Object random() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"随机抽取一位客户");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pds = new PageData();
		pds.put("USERNAME", Jurisdiction.getUsername());
		
		
		List<PageData>	varList1 = companyService.listAll(pds);	//列出Customer列表
		
		
		if(varList1.size()>0) {
			//随机生成数组下标、
			int num = (int)(Math.random() * 1000);
		
			while (num>varList1.size()-1) {
			    if (num<=varList1.size()-1) {
			break;
			}
			     num = (int)(Math.random() * 1000);	
			}
			
			errInfo ="false";  
			
			System.out.println("卧槽------是我是我是我是我-----》"+varList1.get(num).getString("NAME"));
			
			map.put("name", varList1.get(num).getString("NAME"));
		}
		
		map.put("result", errInfo);		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**随机抽取1
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/random1")
	@ResponseBody
	public Object random1() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"随机抽取一位客户");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pds = new PageData();
		pds.put("USERNAME", Jurisdiction.getUsername());
		pds.put("LEVEL", "a");
		
		List<PageData>	varList1 = companyService.listAll(pds);	//列出Customer列表
		
		
		if(varList1.size()>0) {
			//随机生成数组下标、
			int num = (int)(Math.random() * 1000);
		
			while (num>varList1.size()-1) {
			    if (num<=varList1.size()-1) {
			break;
			}
			     num = (int)(Math.random() * 1000);	
			}
			
			errInfo ="false";  
			
			System.out.println("卧槽------是我是我是我是我-----》"+varList1.get(num).getString("NAME"));
			
			map.put("name", varList1.get(num).getString("NAME"));
		}
		
		map.put("result", errInfo);		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**随机抽取2
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/random2")
	@ResponseBody
	public Object random2() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"随机抽取一位客户");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pds = new PageData();
		pds.put("USERNAME", Jurisdiction.getUsername());
		pds.put("LEVEL", "aa");
		
		List<PageData>	varList1 = companyService.listAll(pds);	//列出Customer列表
		
		
		if(varList1.size()>0) {
			//随机生成数组下标、
			int num = (int)(Math.random() * 1000);
		
			while (num>varList1.size()-1) {
			    if (num<=varList1.size()-1) {
			break;
			}
			     num = (int)(Math.random() * 1000);	
			}
			
			errInfo ="false";  
			
			System.out.println("卧槽------是我是我是我是我-----》"+varList1.get(num).getString("NAME"));
			
			map.put("name", varList1.get(num).getString("NAME"));
		}
		
		map.put("result", errInfo);		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**随机抽取3-21
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/random3")
	@ResponseBody
	public Object random3() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"随机抽取一位客户");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		
		PageData pd = new PageData();
		pd = this.getPageData();	
		String key = pd.getString("random");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pds = new PageData();
		pds.put("USERNAME", Jurisdiction.getUsername());
		
		switch(key) {
		case "3":pds.put("LEVEL", "aaa");
		break;
		case "4":pds.put("LEVEL", "aaaa");
		break;
		case "5":pds.put("LEVEL", "aaaaa");
		break;
		case "6":pds.put("LEVEL", "aaaaaa");
		break;
		case "7":pds.put("LEVEL", "aaaaaaa");
		break;
		case "8":pds.put("LEVEL", "aaaaaaaa");
		break;
		case "9":pds.put("LEVEL", "aaaaaaaaa");
		break;
		case "10":pds.put("LEVEL", "aaaaaaaaaa");
		break;
		case "11":pds.put("LEVEL", "aaaaaaaaaaa");
		break;
		case "12":pds.put("LEVEL", "aaaaaaaaaaaa");
		break;
		case "13":pds.put("LEVEL", "aaaaaaaaaaaaa");
		break;
		case "14":pds.put("LEVEL", "aaaaaaaaaaaaaa");
		break;
		case "15":pds.put("LEVEL", "aaaaaaaaaaaaaaa");
		break;
		case "16":pds.put("LEVEL", "aaaaaaaaaaaaaaaa");
		break;
		case "17":pds.put("LEVEL", "aaaaaaaaaaaaaaaaa");
		break;
		case "18":pds.put("LEVEL", "aaaaaaaaaaaaaaaaaa");
		break;
		case "19":pds.put("LEVEL", "aaaaaaaaaaaaaaaaaaa");
		break;
		case "20":pds.put("LEVEL", "aaaaaaaaaaaaaaaaaaaa");
		break;
		case "21":pds.put("LEVEL", "aaaaaaaaaaaaaaaaaaaaa");
		break;
		case "22":pds.put("LEVEL", "aaaaaaaaaaaaaaaaaaaaaa");
		break;
		case "23":pds.put("LEVEL", "aaaaaaaaaaaaaaaaaaaaaaa");
		break;
		case "24":pds.put("LEVEL", "aaaaaaaaaaaaaaaaaaaaaaaa");
		break;
		
		}
		
		
		List<PageData>	varList1 = companyService.listAll(pds);	//列出Customer列表
		
		
		if(varList1.size()>0) {
			//随机生成数组下标、
			int num = (int)(Math.random() * 1000);
		
			while (num>varList1.size()-1) {
			    if (num<=varList1.size()-1) {
			break;
			}
			     num = (int)(Math.random() * 1000);	
			}
			
			errInfo ="false";  
			
			System.out.println("卧槽------是我是我是我是我-----》"+varList1.get(num).getString("NAME"));
			
			map.put("name", varList1.get(num).getString("NAME"));
		}
		
		map.put("result", errInfo);		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Customer");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		companyService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表1-建筑工程-土建
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list1")
	public ModelAndView list1(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//建筑工程-土建
		pd.put("LEVEL", "a");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list1");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表2-建筑工程-设备
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list2")
	public ModelAndView list2(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//建筑工程-设备
		pd.put("LEVEL", "aa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list2");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表3-建筑工程-建筑爆破与拆除
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list3")
	public ModelAndView list3(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//建筑工程-建筑爆破与拆除
		pd.put("LEVEL", "aaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list3");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表4-电力工程-火电设备
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list4")
	public ModelAndView list4(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//电力工程-火电设备
		pd.put("LEVEL", "aaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list4");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表5-电力工程-送变电
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list5")
	public ModelAndView list5(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//电力工程-送变电
		pd.put("LEVEL", "aaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list5");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表6-工业设备安装工程-起重运输设备
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list6")
	public ModelAndView list6(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//工业设备安装工程-起重运输设备
		pd.put("LEVEL", "aaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list6");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表7-工业设备安装工程-化工械设备
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list7")
	public ModelAndView list7(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//工业设备安装工程-化工械设备
		pd.put("LEVEL", "aaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list7");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表8-工业设备安装工程-自动化
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list8")
	public ModelAndView list8(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//工业设备安装工程-自动化
		pd.put("LEVEL", "aaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list8");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表9-市政公用工程-城市道路、桥梁
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list9")
	public ModelAndView list9(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//市政公用工程-城市道路、桥梁
		pd.put("LEVEL", "aaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list9");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表10-市政公用工程-城市供、排水及污水处理
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list10")
	public ModelAndView list10(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//市政公用工程-城市供、排水及污水处理
		pd.put("LEVEL", "aaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list10");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表11-公路工程-公路
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list11")
	public ModelAndView list11(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//公路工程-公路
		pd.put("LEVEL", "aaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list11");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表12-公路工程-桥梁及隧道
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list12")
	public ModelAndView list12(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//公路工程-桥梁及隧道
		pd.put("LEVEL", "aaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list12");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表13-铁路-铁路综合性工程
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list13")
	public ModelAndView list13(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//铁路-铁路综合性工程
		pd.put("LEVEL", "aaaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list13");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表14-建筑装饰工程-公共装饰
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list14")
	public ModelAndView list14(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//建筑装饰工程-公共装饰
		pd.put("LEVEL", "aaaaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list14");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表15-建筑装饰工程-古建筑
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list15")
	public ModelAndView list15(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//建筑装饰工程-古建筑
		pd.put("LEVEL", "aaaaaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list15");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表16-建筑装饰工程-公共展示
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list16")
	public ModelAndView list16(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//建筑装饰工程-公共展示
		pd.put("LEVEL", "aaaaaaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list16");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	

	/**列表17-建筑幕墙-建筑幕墙
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list17")
	public ModelAndView list17(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//建筑幕墙-建筑幕墙
		pd.put("LEVEL", "aaaaaaaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list17");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表18-工程造价-土建装饰
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list18")
	public ModelAndView list18(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//工程造价-土建装饰
		pd.put("LEVEL", "aaaaaaaaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list18");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表19-工程造价-水电安装
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list19")
	public ModelAndView list19(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//工程造价-水电安装
		pd.put("LEVEL", "aaaaaaaaaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list19");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表20-工程造价-园林绿化
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list20")
	public ModelAndView list20(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//工程造价-园林绿化
		pd.put("LEVEL", "aaaaaaaaaaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list20");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表21-工程造价-市政道路
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list21")
	public ModelAndView list21(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//工程造价-市政道路
		pd.put("LEVEL", "aaaaaaaaaaaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list21");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表22-建筑工人技术
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list22")
	public ModelAndView list22(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//建筑工人技术
		pd.put("LEVEL", "aaaaaaaaaaaaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list22");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表23-特种作业人员
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list23")
	public ModelAndView list23(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//特种作业人员
		pd.put("LEVEL", "aaaaaaaaaaaaaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list23");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表24-现场专业人员
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list24")
	public ModelAndView list24(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Customer");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastStart");	//开始时间
		String lastLoginEnd = pd.getString("lastEnd");		//结束时间
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("USERNAME", Jurisdiction.getUsername());
		//现场专业人员
		pd.put("LEVEL", "aaaaaaaaaaaaaaaaaaaaaaaa");
		   
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		page.setPd(pd);
		List<PageData>	varList = companyService.list(page);	//列出Customer列表
		mv.setViewName("erp/company/company_list24");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("USERNAME", Jurisdiction.getUsername());	//用户名
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		mv.setViewName("erp/company/company_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = companyService.findById(pd);	//根据ID读取
		pd.put("USERNAME", Jurisdiction.getUsername());	//用户名
		List<PageData>	companyTypeList = companyTypeService.listAll(pd);
		mv.setViewName("erp/company/company_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.addObject("companyTypeList", companyTypeList);
		return mv;
	}	
	
	 /**查看页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goView")
	public ModelAndView goView()throws Exception{
		ModelAndView mv = this.getModelAndView();
		//查询客户订单份数
		PageData pds = new PageData();
		pds = this.getPageData();
		//得到客户信息
		PageData pd = new PageData();
		pd = companyService.findById(pds);	//根据ID读取
		//订单信息统计

		
		List<PageData>	varList = customerService.listCompanyAll(pds);	//列出该客户的所有订单
		pd.put("COUNTBILL", varList.size());
		//累计订单金额
		double money = 0;
		//累计实收金额
		double income = 0;
		//累计欠费
		double outmoney = 0;
		//累计利润
		double billfee = 0;
		//累计成本
		double baseMoney = 0;
		for(PageData a : varList) {
			 money= money+ (Double)a.get("MONEY");
			 income= income+(Double)a.get("INCOME");
			 outmoney=outmoney+ (Double)a.get("OUTMONEY");
			 billfee=billfee+ (Double)a.get("BILLFEE");
			 baseMoney = baseMoney + (Double)a.get("QQ");
		}
		
		pd.put("MONEY", money);
		pd.put("INCOME", income);
		pd.put("OUTMONEY", outmoney);
		pd.put("BILLFEE", billfee);
		pd.put("BASEMONEY", baseMoney);
		companyService.edit(pd);
		
		//统计后重新获取值
		pd = companyService.findById(pd);
		
		List<PageData>	varListL = companyTypeService.listAll(pd);
		mv.setViewName("erp/company/company_view");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.addObject("varListL", varListL);
		return mv;
	}
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Customer");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			companyService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Customer到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("姓名");	//1
		titles.add("年龄");	//2
		titles.add("手机");	//3
		titles.add("地址");	//4
		titles.add("QQ");	//5
		titles.add("微信");	//6
		titles.add("建档时间");	//7
		titles.add("消费金额");	//8
		titles.add("级别");	//9
		titles.add("备注1");	//10
		titles.add("备注2");	//11
		dataMap.put("titles", titles);
		pd.put("USERNAME", Jurisdiction.getUsername());
		List<PageData> varOList = companyService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("NAME"));	    //1
			vpd.put("var2", varOList.get(i).getString("AGE"));	    //2
			vpd.put("var3", varOList.get(i).get("PHONE").toString());	//3
			vpd.put("var4", varOList.get(i).getString("ADDRESS"));	    //4
			vpd.put("var5", varOList.get(i).get("QQ").toString());	//5
			vpd.put("var6", varOList.get(i).getString("WEIXIN"));	    //6
			vpd.put("var7", varOList.get(i).getString("CTIME"));	    //7
			vpd.put("var8", varOList.get(i).get("MONEY").toString());	//8
			vpd.put("var9", varOList.get(i).getString("LEVEL"));	    //9
			vpd.put("var10", varOList.get(i).getString("REMARKS1"));	    //10
			vpd.put("var11", varOList.get(i).getString("REMARKS2"));	    //11
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
