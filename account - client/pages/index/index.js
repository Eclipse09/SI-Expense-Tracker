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

  //获取今日消费
  getTodayBill: function (openID) {
    let that = this;
    let data = {
      UserNo: openID,
    }
    //统计，今日账单列表
    var url = app.globalData.address + '/testSumFul';
    util.HttpPost(url,data,function(res){
      var ret = JSON.parse(res.message);
      that.setData({
        todayMoney: ret.sysdateAmount == null ? 0 : ret.sysdateAmount,
        //monthExpend: ret.curdateAmount == null ? 0 : ret.curdateAmount,
        //yearExpend: ret.yearAmount == null ? 0 : ret.yearAmount,
        //todayRecord: ret.todayRecord,
      })        
    });
  },

  //今日账单item点击
  onHisterBillItemClick: function (e) {
    let that = this;
    let id = e.currentTarget.dataset.id;
    if (touchEndTime - touchStartTime > 500) {
      wx.showModal({
        title: '提示',
        content: '确认删除本条账单记录',
        success: function (res) {
          if (res.confirm) {
            that.delRecordBill(id);
          }
        }
      })
    } else {
      wx.navigateTo({
        url: '../../pages/record-expend/record-expend?id=' + id,
      })
    }
  },
  //删除记录
  delRecordBill: function (id) {
    let that = this;
    let openID = wx.getStorageSync('openID');
    let url = app.globalData.address + "/delRecordBill";
    let data = {
      UserNo: openID,
      Id: id
    }
    util.HttpPost(url, data, function (res) {
      if (res.code == 1) {
        that.getTodayBill(openID);
        that.getHistoryBillList();
        //that.onShow();
      }
    })
  },

  //请求服务获取历史账单
  getHistoryBillList: function () {
    let that = this;
    let openID = app.globalData.openID;
    let url = app.globalData.address + "/queryTime";
    let data = {
      UserNo: openID,
      startDate: that.data.startDate,
      endDate: that.data.endDate,
      PageSize: 20,
      PageIndex: 0,
    }
    util.HttpPost(url, data, function (res) {
      var ret = JSON.parse(res.message)
      that.setData({
        totalMoney: ret.totalMoney == null ? 0 : ret.totalMoney,
        historyBillList: ret.historyBillList,
      });
    })
  },

  onShareAppMessage: function () {
    return {
      title: '账单',
      path: 'pages/index/index',
      success: function (res) {
        // 分享成功
      },
      fail: function (res) {
        // 分享失败
      }
    }
  },
  
  //更改开始日期
  startDateChange: function (e) {
    this.setData({
      startDate: e.detail.value
    });
    this.getHistoryBillList();
  },

  //更改结束日期
  endDateChange: function (e) {
    this.setData({
      endDate: e.detail.value
    });
    this.getHistoryBillList();
  },

  onTouchStart: function (e) {
    touchStartTime = e.timeStamp;
  },

  onTouchEnd: function (e) {
    touchEndTime = e.timeStamp;
  },

  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    var startDate = util.addDay(-7);
    this.setData({
      startDate: util.formatTime(startDate, "yyyy-MM-dd"),
      endDate: util.formatTime(new Date(), "yyyy-MM-dd"),
      today: util.formatTime(new Date(), "yyyy-MM-dd"),
    });
    //this.getHistoryBillList();
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    let that = this;
    let openID = app.globalData.openID;
    if (openID != "" && openID != null) {
      that.getTodayBill(openID);
      that.getHistoryBillList();
    } else {
      //调用登录接口
      app.getUserInfo(function (openID) {
        if (openID != "") {
          that.getTodayBill(openID);
          that.getHistoryBillList();
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
