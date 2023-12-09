/* eslint-disable jsx-a11y/control-has-associated-label */
/* eslint-disable no-console */
/* external import */
import React from 'react';
import { useGoogleLogin } from '@react-oauth/google';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { message } from 'antd';
/* service */
import { fetchUserData } from '../../service/LoginSignup.service';
/* constant */
import { TOASTER_MSG } from '../../constants/LoginSignup.constant';
/* styles */
import styles from './GoogleAuth.module.scss';

function GoogleAuth() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const login = useGoogleLogin({
    onSuccess: async (tokenResponse) => {
      try {
        await dispatch(fetchUserData(tokenResponse?.access_token));
        navigate('/home');
      } catch (error) {
        message.error(TOASTER_MSG.FAILED_WHILE_FETCHING_USER_DATA);
      }
    },
    onError: () => {
      message.error(TOASTER_MSG.LOGIN_FAILED);
    },
  });

  const handleOnLoginClick = () => {
    login();
  };

  return (
    <div
      role="button"
      tabIndex={0}
      className={styles.socialContainer}
      onClick={handleOnLoginClick}
    >
      <i className="fab fa-google-plus-g" />
    </div>
  );
}

export default GoogleAuth;
