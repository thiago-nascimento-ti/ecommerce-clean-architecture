import React from 'react';
import ReactDOM from 'react-dom';
import App from './presentation/App';
import { store } from './presentation/app/store';
import { Provider } from 'react-redux';
import 'antd/dist/antd.css';

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);