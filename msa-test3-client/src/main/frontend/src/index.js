import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

import {
  BrowserRouter,
  Routes,
  Route,
  Navigate
} from "react-router-dom";

//import Dashboard from './dashboard/Dashboard';
import DashboardMain from './dashboard/DashboardMain';
import OrdersMain from './orders/OrdersMain';
import Customers from './customers/Customers';
import Customer from './customers/Customer';
import ReportsMain from './reports/ReportsMain';

//const root = ReactDOM.createRoot(document.getElementById('root'));
//root.render(
//  <React.StrictMode>
//    <App />
//  </React.StrictMode>
//);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
            <Route path="/" element={<Navigate to="/dashboard" />} />
            <Route path="dashboard" element={<DashboardMain />} />
            <Route path="orders" element={<OrdersMain />} />
            <Route path="customers" element={<Customers />} />
            <Route path="customer" element={<Customer />} />
            <Route path="reports" element={<ReportsMain />} />
        </Route>
      </Routes>
    </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
