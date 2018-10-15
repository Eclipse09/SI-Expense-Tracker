var wxCharts = require('../../utils/wxcharts.js')
var util = require('../../utils/util.js')
var app = getApp();
var lineChart = null;
Page({
  data: {
    MonthDate: [],
    Money: []
  },
  touchHandler: function (e) {
    lineChart.showToolTip(e, {
      // background: '#7cb5ec'
    });
  },
  //////
  getChartsData: function (){
    let that = this;
    let openID = wx.getStorageSync('openID');
    let data = {
      UserNo: openID
    };
    var url = app.globalData.address + '/queryList';
    util.HttpPost(url,data,function(res){
      var ret = JSON.parse(res.message)
      that.setData({
        MonthDate: ret.listTime,
        Money: ret.listAmount,
      })  
    that.initCharts(that.data.MonthDate, that.data.Money)
    })
  },
 
   initCharts: function (date,money) {
    var windowWidth = 320;
    try {
      var res = wx.getSystemInfoSync();
      windowWidth = res.windowWidth;
    } catch (e) {
      console.error('getSystemInfoSync failed!');
    }

    lineChart = new wxCharts({
      canvasId: 'lineCanvas',
      type: 'line',
      categories: date,
      animation: true,
      background: '#f5f5f5',
      series: [{
        name: '每月总支出',
        data: money,
        format: function (val, name) {
          val = parseFloat(val);
          return val.toFixed(1);
        }
      }],
      xAxis: {
        disableGrid: true
      },
      yAxis: {
        title: '花费金额 (元)',
        format: function (val) {
          val = parseFloat(val);
          return val.toFixed(1);
        },
        min: 0
      },
      width: windowWidth,
      height: 200,
      dataLabel: true,
      dataPointShape: true,
      
    });

  },

  onShareAppMessage: function () {
    return {
      title: '近日账单',
      path: 'pages/index/index',
      success: function (res) {
        // 分享成功
      },
      fail: function (res) {
        // 分享失败
      }
    }
  },

  onShow:function(){
    this.getChartsData();
  }
});