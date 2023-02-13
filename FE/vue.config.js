const { defineConfig } = require("@vue/cli-service");

module.exports = defineConfig({
  transpileDependencies: true,
  css: {
    loaderOptions: {
      sass: {
        additionalData: `
          @import "@/assets/scss/common.scss";
          @import "@/assets/scss/reset.scss";
          @import "@/assets/scss/variables_color.scss";
        `,
      },
    },
  },
  chainWebpack: (config) => {
    const svgRule = config.module.rule("svg");
    svgRule.uses.clear();
    svgRule.delete("type");
    svgRule.delete("generator");
    svgRule
      .use("babel-loader")
      .loader("babel-loader")
      .end()
      .use("svg-vue3-loader")
      .loader("svg-vue3-loader");
  },
  devServer: {
    port: 3000,
    liveReload: true,
    // host 지정
    host: "43.201.55.159",
    allowedHosts: "all",
    open: true,
    client: {
      overlay: true,
      // 웹소켓용 url 지정
      webSocketURL: "ws://0.0.0.0:80/ws",
    },
    compress: true,
  },
});
