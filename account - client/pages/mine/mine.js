//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
      avatarUrl:null,
      nickName:"",
  },

  //跳转月账单页面
  exportBill:function(){
    wx.navigateTo({
      url: '../../pages/month-bill/month-bill'
    })
  },

  onLoad: function () {
    let userInfo = app.globalData.userInfo;
    this.setData({
      avatarUrl: userInfo.avatarUrl,
      nickName: userInfo.nickName,
    });
  },
  onShow:function(){
  }
})