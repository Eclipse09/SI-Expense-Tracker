// pages/record-expend/record-expend.js
var util = require('../../utils/util.js');
var QQMapWX = require('../../utils/qqmap-wx-jssdk.js');
const app = getApp();
Page({
  data: {
    consumePatternsList: [
      { "Name": "食物", "Icon": "../../img/food_d.png", "IconSel": "../../img/food_p.png", "IsSelect": true},
      { "Name": "交通", "Icon": "../../img/traffic_d.png", "IconSel": "../../img/traffic_p.png", "IsSelect": false },
      { "Name": "淘宝", "Icon": "../../img/taobao_d.png", "IconSel": "../../img/taobao_p.png", "IsSelect": false },
      { "Name": "娱乐", "Icon": "../../img/entertainment_d.png", "IconSel": "../../img/entertainment_p.png", "IsSelect": false },
      { "Name": "房租", "Icon": "../../img/home.png", "IconSel": "../../img/home_c.png", "IsSelect": false },
      { "Name": "其他", "Icon": "../../img/other_d.png", "IconSel": "../../img/other_p.png", "IsSelect": false }
      ],
    id: "",
    date: "",//日期
    selectName: "食物",//选择的消费方式
    selectImg: "../../img/food_p.png",
    isShowCaculator: true,//是否显示计算器（隐藏为空）
    spendMoney: "0.00",
    remarksText: "",
    caculatorEnd: true,//计算完毕，重新开始
    todayDate: "",
  },

  //选择消费去处
  onConsumptionItemClick: function (e) {
    var index = e.currentTarget.dataset.index;
    console.info(index)
    var list = this.data.consumePatternsList;
    for (var key of list) {
      key.IsSelect = false;
    }
    list[index].IsSelect = true;
    this.setData({
      consumePatternsList: list,
      selectName: list[index].Name,
      selectImg: list[index].IconSel,
    });
  },

  //选择时间
  onDateChange: function (e) {
    this.setData({
      date: e.detail.value,
    });
  },

  //显示计算器
  showCaculator: function () {
    this.setData({
      isShowCaculator: true,
    });
  },

  //隐藏计算器
  hiddenCaculator: function () {
    this.setData({
      isShowCaculator: false,
    });

  },

  //备注输入框
  onInputRemarks: function (e) {
    var text = e.detail.value;
    this.setData({
      remarksText: text,
    });
    if (this.data.isShowCaculator) {
      this.hiddenCaculator();
    }
  },

  //完成输入花费
  confirmData: function () {
    //未计算结果
    if (!this.data.caculatorEnd) {
      this.caculatorResult();
    }
    if (parseFloat(this.data.spendMoney) == 0) {
      wx.showToast({
        title: '请输入花费金额',
        duration: 2000
      });
      return;
    }
    this.addRecordBill();
  },

  //获取消费方式
  getSpendWayList: function () {
    let that = this;
    let openID = app.globalData.openID;
    let data = {
      UserNo: openID,
    };
  },

  //获取消费详情
  getBillDetailInfo: function () {
    let that = this;
    console.info(that.data.id)
    if(that.data.id == ""){
      return;
    }
    let data = {
      id: that.data.id,
    };
    let url = app.globalData.address + "/queryById";
    util.HttpPost(url, data, function(res){
      var billInfo = JSON.parse(res.message).billInfo;
      that.setData({
        date: billInfo.createTime,
        spendMoney: billInfo.amount,
        remarksText: billInfo.remark,
      })
    })
  },


  //保存账单到服务
  addRecordBill: function () {
    let openID = app.globalData.openID;
    let data = {
      UserNo: openID,
      Date: this.data.date,
      SpendMoney: this.data.spendMoney,
      Remarks: this.data.remarksText,
      SpendWay: this.data.selectName,
      SpendWayImg: this.data.selectImg,
      location:this.data.province + this.data.city,
    };
    var url = app.globalData.address + '/saveFul';
    util.HttpPost(url,data,function(res){
      var ret = JSON.parse(res.message);
      if (ret.falg == '0'){
        wx.showToast({
          title: '保存成功',
          duration: 2000
        });
        wx.switchTab({
          url: '../../pages/index/index'
        })
      }
    })
  },

  ////////计算器相关
  touchNum: function (e) {
    var text = e.currentTarget.dataset.num;
    var num = "";
    if (text == "+") {
      this.setData({
        caculatorEnd: false,
      });
    }
    if (parseFloat(this.data.spendMoney) != 0) {
      num = this.data.spendMoney;
      if (this.data.caculatorEnd) {
        num = "";
      }
    }
    num = num + text;
    this.setData({
      spendMoney: num == 0 ? "0.00" : num,
      caculatorEnd: false,
    });
  },
  //删除一个字符
  touchClear: function () {

    if (parseFloat(this.data.spendMoney) != 0) {
      var text = this.data.spendMoney;
      text = text.substring(0, text.length - 1);

      this.setData({
        spendMoney: text == 0 ? "0.00" : text,
      });
    }
  },

  //计算结果
  touchResult: function () {
    this.caculatorResult();
  },


  caculatorResult: function () {
    if (parseFloat(this.data.spendMoney) != 0) {
      var result = this.data.spendMoney + "";
      var strResult = result.split("+");
      var sum = 0;
      strResult.forEach(function (num) {
        sum += parseFloat(num == "" ? 0 : num)
      });
      this.setData({
        spendMoney: sum == 0 ? "0.00" : sum,
        caculatorEnd: true,
      });
      this.hiddenCaculator();
    }
  },
  
  //获取当前坐标
  getLocation: function () {
    var that = this
    wx.getLocation({
      type: 'wgs84',
      success: function (res) {
        console.log(JSON.stringify(res))
        var latitude = res.latitude
        var longitude = res.longitude
        that.getLocal(latitude, longitude);
      },
      fail:function(res){
        console.log('fail+'+JSON.stringify(res))
      }
    });
  },

  //根据坐标获取位置
  getLocal: function (latitude, longitude){
    var that = this
    var qqmapsdk = new QQMapWX({
      key: 'L4YBZ-WDCCP-LJZDO-VKTYX-CQS4O-P3F7O'
    });
    qqmapsdk.reverseGeocoder({
      location: {
        latitude: latitude,
        longitude: longitude
      },
    success:function(res){
      console.log(JSON.stringify(res));
      var province = res.result.ad_info.province
      var city = res.result.ad_info.city
      that.setData({
        province: province,
        city: city
      })
    },
    fail:function(res){
      console.log(res);
    }
  })
  },

  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.setData({
      id: options.id == undefined ? "" : options.id
    });
  },
  onReady: function () {
     this.setData({
      date: util.formatTime(new Date(), "yyyy-MM-dd"),
      todayDate: util.formatTime(new Date(), "yyyy-MM-dd"),
    });
    this.getSpendWayList();
  },
  onShow: function () {
    this.getBillDetailInfo();
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
})