// apollo.config.js
module.exports = {
  client: {
    service: {
      name: 'my-app',
      // GraphQL API 的 URL
      url: 'http://localhost:3000/graphql',
    },
    // 通过扩展名选择需要处理的文件
    includes: [
      'src/**/*.vue',
      'src/**/*.ts',
    ],
  },
}