import React from 'react';
import { useSelector } from 'react-redux';
import { Navigate, Outlet } from 'react-router-dom';

function PrivateRoute() {
  const userData = useSelector(state => state.loginSignupReducer.userData);
  return (
    (userData
    && Object.keys(userData).length > 0
    && (userData?.username !== ''))
      ? <Outlet />
      : <Navigate to="/" />
  );
}

export default PrivateRoute;
