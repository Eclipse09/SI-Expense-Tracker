//app.js
var util = require('/utils/util.js')
App({
  getUserInfo: function (cb) {
    var that = this
    //调用登录接口
    wx.login({
      success: function (Res) {
        wx.getUserInfo({
          success: function (res) {
            that.globalData.userInfo = res.userInfo
            let data = {
              code: Res.code,
            }
            var url = 'https://www.tuanti.xyz/shdc_bss/priceSumRestFul/queryByCode';
            util.HttpPost(url, data, function (res) {
              that.globalData.openID = res.openid;
              typeof cb == "function" && cb(that.globalData.openID)
            });
          }
        })
      }
    })
  },
  globalData: {
    address: 'https://www.tuanti.xyz/shdc_bss/priceSumRestFul',
    userInfo: null,
    openID: "",
    wxappName:"SI记账"
  }
})