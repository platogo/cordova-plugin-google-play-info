var exec = require("cordova/exec");

module.exports = {
  getVersion: function () {
    return new Promise(function (resolve, reject) {
      exec(resolve, reject, "GooglePlayInfo", "getVersion", []);
    });
  },
};
