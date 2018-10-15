//index.js
//获取应用实例
var util = require('../../utils/util.js')
var app = getApp();
var touchStartTime = 0;
var touchEndTime = 0;
var canClick = true;
Page({
  data: {
    startDate: "",
    endDate: "",
    today: "",
    todayMoney: 0,
    totalMoney: 0,
    historyBillList: [
    ],
    scrollHeight: "100%",  
  },

  //请求服务获取当月账单
  getMonthBillList: function (openID) {
    let that = this;
    let url = app.globalData.address + "/queryMonthBill";
    let data = {
      UserNo: openID,
      PageSize: 20,
      PageIndex: 0,
    }
    util.HttpPost(url, data, function (res) {
      var ret = JSON.parse(res.message)
      that.setData({
        totalMoney: ret.totalMoney == null ? 0 : ret.totalMoney,
        monthBillList: ret.monthBillList,
      });
    })
  },

  //获取今日消费
  getTotalAmount: function (openID) {
    let that = this;
    let data = {
      UserNo: openID,
    }
    //统计，今日账单列表
    var url = app.globalData.address + '/testSumFul';
    util.HttpPost(url, data, function (res) {
      var ret = JSON.parse(res.message);
      that.setData({
        monthMoney: ret.curdateAmount == null ? 0 : ret.curdateAmount
      })
    });
  },

  //返回至我的账户页面
  backTomine: function(){
    wx.navigateBack();
  },

  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    var startDate = util.addDay(-7);
    this.setData({
      year: util.formatTime(new Date(), "yyyy"),
      month: util.formatTime(new Date(), "MM"),
    });
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    let that = this;
    let openID = app.globalData.openID;
    if (openID != "" && openID != null) {
      that.getMonthBillList(openID);
      that.getTotalAmount(openID);
    } else {
      //调用登录接口
      app.getUserInfo(function (openID) {
        if (openID != "") {
          that.getMonthBillList(openID);
          that.getTotalAmount(openID);
        }
      });
    }
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }
})