module.exports = {
  pluginOptions: {
    vuetify: {
      // https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
    },
    css: {
      loaderOptions: {
        scss: {
          additionalData: `@import "@/styles/sass/variables.scss";`,
        },
      },
    },
  },
};
