/* external imports */
import React from 'react';
import { Route, Routes } from 'react-router-dom';

/* internal components */
import Home from '../../pages/Home';
import Feed from '../../pages/Postpage/Components/feed';
import App from '../App';
import PageNotFound from '../configs/PageNotFound';
import ForgetPassword from '../../components/organisms/LoginSignup/components/forgetPassword';
import Chat from '../../pages/Chat';
import PrivateRoute from './PrivateRoute';

function RouteConfig() {
  return (
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="/home" element={<PrivateRoute />}>
        <Route index element={<Home />} />
      </Route>
      <Route path="/chat" element={<PrivateRoute />}>
        <Route index element={<Chat />} />
      </Route>
      <Route path="/feed/:category/:categoryId" element={<PrivateRoute />}>
        <Route index element={<Feed />} />
      </Route>
      <Route path="/resetpasswordSS" element={<ForgetPassword />} />
      <Route path="*" element={<PageNotFound />} />
    </Routes>
  );
}

export default RouteConfig;
