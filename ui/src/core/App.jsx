/* external imports */
import React from 'react';
/* styles */
import './index.scss';
/* internal components */
import LoginSignup from '../components/organisms/LoginSignup/components';
import withNavbar from '../resources/shared/hoc/navbar';

function App() {
  return (
    <LoginSignup />
  );
}

export default withNavbar(App);
