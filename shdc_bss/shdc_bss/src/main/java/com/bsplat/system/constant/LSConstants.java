package com.bsplat.system.constant;

import java.util.HashMap;
import java.util.Map;

public class LSConstants {
	// 翻页 页号
	public static final String DEFAULT_OFFSET = "0";
	// 翻页 显示记录数
	public static final String DEFAULT_PAGE_SIZE = "10";
	// 翻页 显示记录数
	public static final String PAGE_SIZE_50 = "50";

	// 布尔值 0
	public static final String BOOLEAN_FALSE = "0";
	// 布尔值 0
	public static final String BOOLEAN_TRUE = "1";
    
	//判断是否调用锦商锁货接口 false：不调用  true：调用
	public static final Boolean STM_STOCKINFO_FLAG=true;
	/**
	 * 菜单分类 1：融资人 2：仓库 3：监管方 4：银行 5：平台管理员
	 */
	// public static final String MENU_CONSIGNER = "Consigner";//委托人
	// public static final String MENU_VENDOR = "Vendor";//供应商
	// public static final String MENU_BSPLAT_MANAGER = "BsplatManager";//平台管理员
	// public static final String MENU_USER = "User";//商情用户

	public static final String MENU_FINANCIER = "Financier";// 融资人
	public static final String MENU_STORAGE = "Storage";// 仓库
	public static final String MENU_JIANGUAN = "JianGuan";// 监管方
	public static final String MENU_BANK = "Bank";// 银行
	public static final String MENU_BSPLAT_MANAGER = "BcplatManager";// 平台管理员

	public static final String ROLE_TYPE_SESSION = "role_type";// 会员的角色
	public static final String CURR_ROLE_TYPE = "curr_role_type";// 当前角色类型
	public static final String CURR_BUSINESS_TYPE = "curr_business_type";// 当前业务类型
	public static final String COMPANY_NO = "companyNo";// 登陆人会员号
	public static final String COMPANY_NAME = "chineseName";// 会员名称
	public static final String USER_LOGIN_NO = "userLoginNo";// 登录人账号
	public static final String USER_LOGIN_NAME = "userchsName";// 登录人名称
	public static final String USER_CA_NO = "ca_serial_no";// 登录人证书序号
	public static final String USER_CA_LOGNO = "ca_qm_user";// 登录人签名账号
															// 应该和会员登录人账号是一样的

	public static final String PARENT_USER_NUM = "p_user_num";
	public static final String PARENT_USER_NAME = "p_user_name";
	public static final String BRANCH_BANK_LIST = "bank_list";
	
	public static final String IS_SHOW_NAVIGATION = "isShowNavigation";

	// 公司下拉菜单内容信息
	public static String LABEL_TYPE_BANK = "BANK";

	public static String LABEL_TYPE_BANK_BRANCH = "BANK_BRANCH";

	public static String LABEL_TYPE_COMPANY = "COMPANY";

	public static String LABEL_TYPE_CUSTOMER = "CUSTOMER";

	public static String LABEL_TYPE_WAREHOUSE = "WAREHOUSE";

	public static String LABEL_TYPE_SURID = "SURID";

	public static final String CREDIT_LIMITS_STATUS_VALIDATED = "10"; // 授信额度状态有效

	public static final String CREDIT_LIMITS_STATUS_INVALIDATED = "00"; // 授信额度状态无效

	public static final String TRIPLE_AGREEMENT_STATUS_VALIDATED = "10"; // 三方协议状态有效

	public static final String TRIPLE_AGREEMENT_STATUS_INVALIDATED = "00"; // 三方协议状态无效

	public static final String FINANCE_LENDER_TYPE_PIAOJU = "10";// 融资放款类型10 票据

	public static final String FINANCE_LENDER_TYPE_LIUDAI = "20";// 融资放款类型20 流贷

	public static final String CREDIT_TYPE_SUM = "10";// 授信类型 10票据额度+流贷额度=总额度

	public static final String CREDIT_TYPE_EQUAIL = "20";// 授信类型 20票据额度=流贷额度=总额度

	public static final String CREDIT_BILL_TYPE_CONTRACT_COMPLETED = "10";// 票据占用额度还原类型
																			// 10合同赎单完毕返还额度

	public static final String CREDIT_BILL_TYPE_REALTIME = "20";// 票据占用额度还原类型
																// 20实时金额还款返还额度

	// 融资申请单状态

	public static final String FC_APPLICATION_SELECT_PLEDGE = "05"; // 货押：待选择质押物状态；仓单：待选择仓单；

	public static final String FC_APPLICATION_PLEDGE_CHECK = "15"; // 货押：质物待审核状态；仓单：待提交仓库；
	
	public static final String FC_APPLICATION_BANK_LEVEL_TWO_CHECK="30"; //待二级审核
	
	public static final String FC_APPLICATION_BANK_LEVEL_THREE_CHECK="40"; //待三级审核
	public static final String FC_APPLICATION_SETTLE_UP = "95"; // 已结清

	public static final String FC_APPLICATION_ENFORCE_CHARGE = "96"; // 强制解押
	
	// 融资申请单状态
	public static final String APPLICATION_HAS_REPEAL = "00";// 异常

	public static final String FC_APPLICATION_SUBMIT = "10"; // 申请

	public static final String FC_APPLICATION_BANK_CHECK = "20"; // 待银行审核状态
	
	public static final String FC_APPLICATION_BACK_REJECT = "60"; // 已驳回

	public static final String FC_APPLICATION_MAKE_LOANING = "70";// 已受理

	public static final String FC_APPLICATION_MAKE_LOAN = "80"; // 监管中，放款已确认

	public static final String FC_APPLICATION_SQUARE_UP = "81"; // 已完成
	
	// 仓单申请状态
    public static final String WARRANTY_APP_CONFIRM = "05"; // 待仓库复核(制单申请)
    public static final String WARRANTY_APP_CANCLE = "00"; // 仓库驳回
    public static final String WARRANTY_APP_CONFIRMED = "10"; // 已生成仓单
    public static final String WARRANTY_APP_JIANGUAN="11";//待监管公司审核
    public static final String WARRANTY_APP_JIANGUAN_CANCLE="01";//监管公司驳回
    public static final String WARRANTY_APP_PT="20";//待平台公司审核
    public static final String WARRANTY_APP_PT_CANCLE="21";//平台公司驳回
	
    //制单申请生成方式（0:仓库预生成  1:融资人申请生成）	
	public static final String WARRANTY_APP_GENETYPE_CANGKU="0"; //仓库预生成 
	public static final String WARRANTY_APP_GENETYPE_FINANCE="1";//融资人申请生成
	// 仓单状态
	public static final String WARRANTY_PREREND="02"; //预生成
    public static final String WARRANTY_CONFIRM = "05"; // 已生成（已解押的仓单现也描述成-已生成）
	public static final String WARRANTY_PRINT = "10"; // 已打印	
	public static final String WARRANTY_CUNSTOMER_CONFIRM = "20"; // 待质押-用户转让背书
	//public static final String WARRANTY_WAREHOUSE_CONFIRM = "30"; // 待仓库转让记录背书
	//public static final String WARRANTY_BACK_CONFIRM = "50"; // 待质押（待银行确认）
	public static final String WARRANTY_BANK_CONFIRMED = "51"; // 已质押
	public static final String WARRANTY_BANK_MORTGAGE = "60"; // 已生成（原已解押）已解押的仓单现描述成-已生成
	public static final String WARRANTY_CANCLE="99"	;//撤消
	public static final String WARRANTY_LOGINOUT_CHECKCK="00"	;//仓单注销待仓库审核
	public static final String WARRANTY_LOGINOUT_CHECKJG="03"	;//仓单注销待监管公司审核
	public static final String WARRANTY_PRELOGINOUT="01"	;//已注销
	
	
	//StockInfo货物状态(质押标志)
	public static final String WARRANTY_STOCK_INITIAL="0";
	public static final String WARRANTY_STOCK_CHUZHI="1";
	public static final String WARRANTY_STOCK_LOCK= "2";
	public static final String WARRANTY_STOCK_UNLOCK="3";
	public static final String WARRANTY_STOCK_JIEYA="4";
	public static final String WARRANTY_STOCK__HASCHOICE="5";
	public static final String WARRANTY_STOCK_WARRANTY="8";
	
	
	
	// 质押清单状态
	public static final String MORTGAGE_CUSTOMER_CREATE = "05"; // 已生成
	public static final String MORTGAGE_WAREHOUSE_CONFIRM = "10"; // 待仓库确认
	public static final String MORTGAGE_WAREHOUSE_CONFIRMNAME = "待仓库确认";
	public static final String MORTGAGE_SUPERVIOR_CHECK = "20"; // 待监管公司审核
	public static final String MORTGAGE_SUPERVIOR_CHECKNAME = "待监管公司审核";
	public static final String MORTGAGE_FUTONG_CHECK = "30"; // 待付通现场审核
	public static final String MORTGAGE_FUTONG_CHECKNAME = "待付通现场审核";
	public static final String MORTGAGE_FUTONG_SYSTEM_CHECK = "31"; // 待付通平台初审
	public static final String MORTGAGE_FUTONG_SYSTEM_CHECKNAME = "待付通平台初审";
	public static final String MORTGAGE_SUBMIT = "40"; // 待提交
	public static final String MORTGAGE_BANK_CHECK = "50"; // 待银行审核
	public static final String MORTGAGE_MAKE_LOAN = "51"; // 已放款
	public static final String MORTGAGE_SQUARE_UP = "60"; // 已结清
	public static final String MORTGAGE_REVOKED = "99"; // 已撤销

	// 赎单状态
	public static final String REDEEM_CANCLE = "XX";// 已撤销
	public static final String REDEEM_RZ_TOSUBMIT = "00";// 待提交
	public static final String REDEEM_WAREHOUSE_CHOUSE = "05";// 待仓库选货
	public static final String REDEEM_BANK_CONFIRM = "10";// 待银行确认
	public static final String REDEEM_BANK_RECONFIRM = "15";// 待银行复核
	public static final String REDEEM_SUPERVISE_TORELEASE = "20";// 待解押
	public static final String REDEEM_SUPERVISE_TOCONFIRM = "21";// 待监管公司确认
	public static final String REDEEM_ADMIN_TORELEASE = "30";// 已解押（待审计）
	public static final String REDEEM_ALREADY_AUDIT = "99";// 已审计

	// 赎货标志
	public static final String REDEEM_FLAG_JS = "10";// 监管解锁
	public static final String REDEEM_FLAG_JY = "20";// 监管解押
	public static final String APPLICATION_HAS_GENERAL = "10";// 已生成
	public static final String APPLICATION_NOT_PASS = "60";// 审批不通过
	
	// 盘点状态
	public static final String MORTGAGE_CHECK_UNTREATED = "30";// 未处理
	public static final String MORTGAGE_CHECK_NORMAL = "10";// 正常
	public static final String MORTGAGE_CHECK_ABNORMAL = "20";// 异常
	/**
	 * 判断插入受信跟踪记录金额正负取值，若放款为负值，收款为正值
	 * 
	 * @author zhengfei
	 */
	public static final Map<String, String> LIMIT_TRACK_POSITIVE_OR_NEGATIVE_MAP = new HashMap<String, String>();
	static {
		LIMIT_TRACK_POSITIVE_OR_NEGATIVE_MAP.put(FC_APPLICATION_SUBMIT, "-");
		LIMIT_TRACK_POSITIVE_OR_NEGATIVE_MAP.put(FC_APPLICATION_MAKE_LOAN, "-");
	}
	public static final String FILE_PATH = "D:/upload/";

	/**
	 * 系统角色定义 1：融资人 2：仓库 3：监管方 4：银行 5：东方付通
	 */
	public static final String FINANCIER = "01";// 融资人
	public static final String STORAGE = "02";// 仓库
	public static final String SUPERVIOR = "03";// 监管方
	public static final String BANK = "04";// 银行
	public static final String ADMINISTRATOR = "05";// 东方钢铁
	public static final String MAINBANK = "04a";// 分行
	public static final String BRANCHBANK = "04b";// 支行
	public static final String MONITOR_COMPANY = "06";// 监管公司
	public static final String SUPERVIOR_CENTER = "07";// 监控中心U01276 
	
	/**
	 * 系统业务类型定义 1：货押 2：厂商银 3：商商银 4：保理 5:仓单 ---与融资协议类型一致
	 */
	public static final String HY = "00";// 货押
	public static final String CSY = "10";// 厂商银
	public static final String SSY = "20";// 商商银
	public static final String BL = "30";// 保理
	public static final String CD = "40";// 仓单
	public static final String DEFAULT_TYPE = "";// 默认业务类型厂商银、商商银其它业务类型开发完成后置为空即可
	public static final String APP_LOAN_STATUS_GENERATE = "10";// 银行放款录入完是10
	public static final String APP_LOAN_STATUS_CONFIRM = "20";// 银行放款复核完发送到erp是20
	public static final String APP_LOAN_STATUS_RED = "30";// 银行放款erp红冲后是30

	/**
	 * 系统标识
	 */
	public static final String BSLS_SYSTEM_ID = "LGS"; // 运输委托
	// 交易系统标识
	public static final String BSOL_SYSTEM_ID = "BSOL";

	// 现货平台系统标识
	public static final String XH_SYSTEM_ID = "XH";

	public static final String SUBMIT_ORDER = "提交";
	// 是否为管理员
	public static final String IS_ADM_Y = "1";
	public static final String IS_ADM_N = "0";

	// 帐号类型 10:管理员，20:业务员，15：副管理员
	public static final String _ADMIN = "10";
	public static final String _SALESMAN = "20";
	public static final String _DEPUTY_ADMIN = "15";

	// 质押类型
	public static final String MORTGAGE_TYPE_10 = "10"; // 按量限
	public static final String MORTGAGE_TYPE_20 = "20"; // 按捆包明细

	// 量限表 type 类型
	public static final String SELECT_WEIGHT_TYPE_0 = "0"; // 融资选货
	public static final String SELECT_WEIGHT_TYPE_1 = "1"; // 赎货选货

	public static final String HY_BILL_STATUS_CS = "0";// 撤销
	public static final String HY_BILL_STATUS_CZ = "1";// 出质
	public static final String HY_BILL_STATUS_ZY = "2";// 质押
	public static final String HY_BILL_STATUS_JS = "3";// 解锁
	public static final String HY_BILL_STATUS_JY = "4";// 解押
	public static final String HY_BILL_STATUS_HWXZ = "5";// 货物被选择
	public static final String HY_BILL_STATUS_CDJS = "11";// 仓单加锁
	public static final String HY_BILL_STATUS_CDZX = "01";// 仓单注销
	public static final String HY_BILL_STATUS_QZJS = "9";// 强制解锁（结清）

	public static final String HY_BILL_ACTTYPE_SD = "1";// 锁定
	public static final String HY_BILL_STATUS_ZJJS = "0";// 直接解锁
	public static final String HY_BILL_STATUS_TDJS = "2";// 提单解锁

	// 调用仓库接口，busstype转化成仓库认可的业务类型（1，2）
	public static final Map<String, String> BUSITyYPE_CONVERT_MAP = new HashMap<String, String>();
	static {
		BUSITyYPE_CONVERT_MAP.put(HY_BILL_STATUS_CS, HY_BILL_STATUS_CZ);
		BUSITyYPE_CONVERT_MAP.put(HY_BILL_STATUS_CZ, HY_BILL_STATUS_CZ);
		BUSITyYPE_CONVERT_MAP.put(HY_BILL_STATUS_CDJS, HY_BILL_STATUS_CZ);
		BUSITyYPE_CONVERT_MAP.put(HY_BILL_STATUS_CDZX, HY_BILL_STATUS_CZ);
		BUSITyYPE_CONVERT_MAP.put(HY_BILL_STATUS_ZY, HY_BILL_STATUS_ZY);
		BUSITyYPE_CONVERT_MAP.put(HY_BILL_STATUS_JS, HY_BILL_STATUS_ZY);
		BUSITyYPE_CONVERT_MAP.put(HY_BILL_STATUS_JY, HY_BILL_STATUS_ZY);
		BUSITyYPE_CONVERT_MAP.put(HY_BILL_STATUS_QZJS, HY_BILL_STATUS_ZY);
	}

	/**
	 * 放款类型与实物票据对应关系
	 */
	public static final Map<String, String> BILL_TYPE_MAP = new HashMap<String, String>();
	static {
		BILL_TYPE_MAP.put(FINANCE_LENDER_TYPE_PIAOJU, "YC");// 票据
		BILL_TYPE_MAP.put(FINANCE_LENDER_TYPE_LIUDAI, "ZZ");// 流贷
	}

	/**
	 * 数据签名业务单据类型定义
	 */

	public static final String BUSINESS_TYPE_MORTGAGE = "MORTGAGE";// 质押清单
	public static final String BUSINESS_TYPE_RELEASE_MOR_NOTICE = "RELEASE_MOR_NOTICE"; // 解押通知书
	public static final String BUSINESS_TYPE_RELEASE_BACK_NOTICE = "RELEASE_BACK_NOTICE"; // 解押回执单
	public static final String BUSINESS_TYPE_WARRANTY = "WARRANTY";//仓单

	/**
	 * 解押通知书状态
	 */

	public static final String RZ_RELEASW_MOR_NOTICE_STATUS_WFK = "00";// 状态解押通知书监管人未反馈
	public static final String RZ_RELEASW_MOR_NOTICE_STATUS_YFK = "10";// 状态解押通知书监管人已反馈
	/**
	 * 报表模板名称定义
	 */

	public static final String TEMPLATE_MORTGAGE = "mortgage";// 质押清单模板
	public static final String TEMPLATE_RELEASE_MOR_NOTICE = "relieve_mortgage_notice"; // 解押通知书模板
	public static final String TEMPLATE_RELEASE_BACK_NOTICE = "relieve_mortgage_notice_back"; // 解押回执单
	public static final String TEMPLATE_LABEL = "labeldemo"; // 标签样章

	// 签名接口方法名
	public static final String RECEIVESIGNCONTRACTDATA = "receiveSignContractData";

	// 签名接口标记为数字签名
	public static final String IS_DIGIGITAL_SIGN = "10";
	// 签名接口标记为单据签名
	public static final String NOT_DIGIGITAL_SIGN = "20";
	// 需要附件签名
	public static final String IS_ANNEX_SIGNATURE="1";
	// 不需要附件签名
	public static final String NOT_ANNEX_SIGNATURE="0";
	// 标记是否重新生成单据信息
	// 重新生成单据信息
	public static final String IS_ALIGN_SIGN = "1";
	// 不重新生成单据信息
	public static final String NOT_ALIGN_SIGN = "0";
	// 融资系统代码
	public static final String SYS_ID = "RZ";

	// 融资在宝盈通系统代码
	public static final String SYS_STM_ID = "STM12";

	// 文件路径变量
	public static final String T_OPERATION_SYS = "T_LSLV_LOGISTICS_ORDER";
	public static final String T_OPERATION_SYS_GROUP = "T_LSLV_LOGISTICS_ORDER_GROUP";

	// 是否调用签名接口 1 不调用 0 调用
	public static final String IS_SIGN_FLAG = "1";

	// 移动调用接口方法名
	public static final String CMD_GETBUSINESSINFOSERVICE_METHOD = "getBusinessInfoService";// 用户登录业务权限数据接口方法
	public static final String CMD_GETMORTGAGECHECKSERVICE_METHOD = "getMortgageCheckService";// 盘点指令接口方法
	public static final String CMD_GETMORDETAILCHECKSERVICE_METHOD = "getMorDetailCheckService";// 盘点指令明细接口方法
	public static final String CMD_SETMORCHECKREPLAYSERVICE_METHOD = "setMorCheckReplyService";// 盘点指令回复接口方法
	public static final String CMD_GETMORCHECKHISTORYSERVICE_METHOD ="getMorCheckHistoryService";//盘点指令历史接口方法
	public static final String CMD_SETDOCUMENTSERVICE_METHOD = "setDocumentService";// 附件上传接口方法
	public static final String CMD_GETDOCUMENTSERVICE_METHOD = "getDocumentService";//附件下载接口方法
	public static final String CMD_GETAPPLICATIONSERVICE_METHOD = "getApplicationService";// 融资申请接口方法
	public static final String CMD_GETAPPLOCATIONSERVICE_METHOD = "getAppLocationService";// 融资申请库位接口方法
	public static final String CMD_GETAPPLOCATIONDETAILSERVICE_METHOD = "getAppLocationDetailService";// 融资申请库位明细接口方法

	// 盘点类型：0 出质 ,1 盘点。
	public static final String  CHECKTYPE_CZ="0";// 出质
    public static final String  CHECKTYPE_PD="1";// 盘点

    // 银行审核记录状态：刚流转过来00；确认并流转05；最终确认10。
    public static final String BANK_CHECK_STATUS_NONE = "00";
    public static final String BANK_CHECK_STATUS_CIRCULATION = "05";
    public static final String BANK_CHECK_STATUS_CONFIRM = "10";
    
    // 银行审核记录单据状态：融资申请单01；赎单10。
    public static final String BANK_CHECK_RECEIPT_TYPE_APP = "01";
    public static final String BANK_CHECK_RECEIPT_TYPE_REDEEM = "10";

    // 预警事项标示 
    public static final String WARNING_FLAG_WARNING = "00";// 预警
    public static final String WARNING_FLAG_HIDEWARNING = "10";// 不提醒
    public static final String WARNING_FLAG_UNDOWARNING = "99";// 解除预警
    
    //FLAG 质押标示0 初始状态 1 出质锁货 2 质押锁货 3 监管解锁 4 监管解押
    public static final String OUT_MORTGAGE_FLAG="1";
    public static final String MORTGAGE_FLAG="2";
    public static final String SUPERVISE_MORTGAGE_FLAG="3";
    
    //MORTGAGE_STATUS质押清单状态
    public static final String MORTGAGE_STATUS="99";
    public static final String MORTGAGE_LIST_STATUS="60";
    
    //附件是否签名 0代表该附件未签名 1代表附件已签名
    public static final String SIGNATURE_ALREADY="1";
    public static final String SIGNATURE_NOT="0";
    
    //沪内户外标记（orc_t_stock_total）
    public static final String INAREA = "沪内";
    public static final String OUTAREA = "沪外";
    
    //品种标记（orc_t_stock_total）
    public static final String TOTALFLAG = "1";
    public static final String TYPEFLAG = "2";
    
    //体系内外标记  0 体系内 1 体系外
    public static final String INISSYSTEM = "0";
    public static final String OUTISSYSTEM = "1";
    
    /**
	 * 
	 * 公告管理是否发布标志
	 */
	//未发布
	public static final String ALIVE_FLAG_REVOKE="0";
	//发布
	public static final String ALIVE_FLAG_UNREVOKE="1";
	
    /**
     * 指数
     */
    public static class INDEX_TYPE {
		/** 库存 1001 */
		public final static String kuCun="1001"; 		
		/** 冷轧 1002 */
		public final static String lenZha="1002";  		
		/** 热轧 1003 */
		public final static String reZha="1003"; 
	}
    
    /**
     * 银行质押登记状态
     */
    public static class BANK_REGISTER_STATUS {
		/** 10 已驳回 */
		public final static String REJECTED="10"; 	
		/** 13 待提交 */
		public final static String TO_SUBMIT="13";
		/** 15 登记申请 */
		public final static String APPLYING="15";  		
		/** 20 已撤销 */
		public final static String REPEALED="20"; 		
		/** 25 登记公示 */
		public final static String NOTICE_FILING ="25"; 
		/** 30 登记异议 */
		public final static String DISAGREEING ="30";		
		/** 35 已登记 */
		public final static String REGISTERED ="35";
		/* 40 委托监管审核 
		public final static String TRUSTEESHIP_APPROVAL ="40";
		 45 展期审核 
		public final static String DEFER_APPROVAL ="45";*/
		/** 50 已结清 */
		public final static String CLOSE_OFF ="50";
		/** 55 异议未登记 */
		public final static String OBJECTION_TO_REGISTER ="55";
		/** 60 已注销 */
		public final static String CANCELED ="60";
	}
    /**
     * 银行质押登记 委托监管审核 状态
     */
    public static class BANK_TRUSTEESHIP_APPROVAL {
		/** 0 待审核 */
		public final static String TO_APPROVAL="0"; 	
		/** 1 审核通过 */
		public final static String APPROVALED ="1";
		
	}
    
    /**
     * 银行质押登记 展期审核 状态
     */
    public static class BANK_DEFER_APPROVAL {
		/** 0 待审核 */
		public final static String TO_APPROVAL="0"; 	
		/** 1 审核通过 */
		public final static String APPROVALED ="1";
		
	}
    
    /**
     * 银行质押登记 融资类型
     */
    public static class BANK_REGISTER_FINANCETYPE {
		/** 0 静态 */
		public final static String STATIC_STATE="0"; 	
		/** 1 动态 */
		public final static String DYNAMIC_STATE ="1";
		
	}
    
    /**
     * 综合查询-业务类型
     */
    /**	业务单据	*/
    public final static String BK_COMPREHENSIVE_DOCUMENT = "0";
    /**	捆包信息	*/
    public final static String BK_COMPREHENSIVE_KBH = "1";
    /**	企业信息	*/
    public final static String BK_COMPREHENSIVE_ENTERPRISE = "2";
    /**	仓单号第一位W	*/
    public final static String BK_COMPREHENSIVE_WAREHOUSE = "W";
    /**	融资申请单FA	*/
    public final static String BK_COMPREHENSIVE_FINANCE = "FA";
    /**	解押申请单SD	*/
    public final static String BK_COMPREHENSIVE_JIEYA = "SD";
    /**	质押登记单PA*/
    public final static String BK_COMPREHENSIVE_ZHIQUAN = "PA";
    /**	质押登记单RA*/
    public final static String BK_COMPREHENSIVE_ZHIQUAN_RA = "RA";
    
    
    /**
     * 银行质押登记 履历操作类型
     */
    public static class BANK_REGISTER_RECORDSTATUS {
    	
    	/** 撤销 10 */
    	public final static String REPEAL="10";
    	/** 新建 11 */
    	public final static String CREATED="11";

    	/** 申请审批驳回 20 */
    	public final static String APP_REJECTED ="20";
    	/** 申请审批通过 21  */
    	public final static String APP_APPROVALED ="21";

    	/** 异议提出 30 */ //objection
    	public final static String OBJECTION_RAISE ="30";
    	/** 异议撤销 31 */
    	public final static String OBJECTION_REPEAL ="31";

    	/** 展期申请 40 */
    	public final static String DEFER_APP ="40";
    	/** 展期审核通过 41 */
    	public final static String DEFER_APPROVALED ="41";
    	/** 展期审核驳回 42 */
    	public final static String DEFER_REJECTED ="42";

    	/** 委托监管申请 50 */
    	public final static String TRUSTEESHIP_APP ="50";
    	/** 委托监管审核通过 51 */
    	public final static String TRUSTEESHIP_APPROVALED ="51";
    	/** 委托监管审核驳回 52 */
    	public final static String TRUSTEESHIP_REJECTED ="52";

    	/**解押 60 */
		public final static String RELEASE ="60";
		
		/**已登记 70 */
		public final static String REGISTERED ="70";
		/**异议未登记 71 */
		public final static String OBJECTION_TO_REGISTER ="71";
		
		/** 80 已注销 */
		public final static String CANCELED ="80";
		
		/** 90 已结清 */
		public final static String CLOSE_OFF ="90";
		
	}
    
    public static final String CHR_ZH = "存货人";
    public static final String CK_ZH = "仓库";
    
    /**	融资申请单状态值集代码 */
    public static final String RZSQD_STATUS_CODESET = "S002";
    
    /**	质押申请单状态值集代码 */
    public static final String ZYSQD_STATUS_CODESET = "S035";
    
    /**维护银行资源信息查询默认仓库*/
    public static final String BANK_RESOURCE_WARE="支付系统测试会员9";
    /**新盘点状态--zjs 2014.2.20*/
    public static final String CHECK_STATUS_WPD="0";//未盘点
    public static final String CHECK_STATUS_PDZ="1";//盘点中
    public static final String CHECK_STATUS_YPD="2";//已盘点
    public static final String CHECK_STATUS_PDCS="3";//已盘点
    
    public static final String STOCK_INFO_STATUS_YC="0";//异常
    public static final String STOCK_INFO_STATUS_ZC="1";//正常

    
    
}
