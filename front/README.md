Front service
=============

This isn't SpringBoot service. It's only one front for all back services.


REQUIREMENTS
------------

1. npm.
2. Babel.
3. Webpack.
4. React-bootstrap.


INSTALLATION
------------

### Installation environment

On command line, type in the following commands (in directory ../front):

      npm install npm@latest -g
      npm install react requirejs react-dom rest --save
      npm install babel-loader babel-core webpack webpack-dev-server @babel/preset-stage-0 babel-plugin-transform-regenerator @babel/preset-env @babel/preset-react --save-dev
      npm install react-bootstrap bootstrap
      npm install --save react-router-dom
      npm install --save react-router-bootstrap

The key "save" does installation just for development.

QUICK START
-----------

For start server to execute script from package json:

      npm run server