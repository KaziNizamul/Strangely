/* eslint-disable no-restricted-syntax */
/* eslint-disable guard-for-in */
/* external imports */
import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { useNavigate } from 'react-router-dom';
import cx from 'classnames';
import { Button, message } from 'antd';
/* utils */
import _get from 'lodash/get';
import LoginSignupUtils from '../../utils/LoginSignup.utils';
import Utility from '../../../../../utils/Utility';
/* constants */
import {
  EMPTY_OBJECT,
  EMPTY_STRING,
  GLOBAL_CONST,
  EMPTY_FUNCTION,
  EMPTY_ARRAY,
} from '../../../../../resources/shared/global.constant';
import { TOASTER_MSG } from '../../constants/LoginSignup.constant';
/* internal components */
import GoogleAuth from '../googleAuth';
/* actions */
import {
  // signUp,
  setLoading,
  updateFields,
  updateUserData,
} from '../../data/LoginSingup.actions';
/* services */
import { userRegistration } from '../../service/LoginSignup.service';
/* styles */
import styles from '../LoginSignup.module.scss';

function SignUp({
  isLoading = false,
  name = EMPTY_STRING,
  email = EMPTY_STRING,
  userData = EMPTY_OBJECT,
  password = EMPTY_STRING,
  phoneNumber = EMPTY_STRING,
  onUpdateFields = EMPTY_FUNCTION,
  onSetLoading = EMPTY_FUNCTION,
  onUpdateUserData = EMPTY_FUNCTION,
}) {
  const navigate = useNavigate();

  useEffect(() => {
    if (Utility.isObjectDefined(userData)) {
      navigate('/home');
    }
  }, [navigate]);

  const handleChange = (evt) => {
    const {
      type,
      target: {
        name: fieldName,
        value,
      },
    } = evt || EMPTY_OBJECT;
    if (type === GLOBAL_CONST.ON_CHANGE) {
      onUpdateFields({
        fieldName,
        value,
      });
    }
  };

  const handleOnSubmit = async (evt) => {
    evt.preventDefault();
    const payload = LoginSignupUtils.getSignUpPayload({
      name,
      email,
      password,
      phoneNumber,
    });
    onSetLoading(true);
    try {
      const response = await userRegistration(payload);
      if (Utility.isObjectDefined(response)) {
        const { data = EMPTY_ARRAY } = response || EMPTY_OBJECT;
        onUpdateUserData(data);
        message.success(data?.message || TOASTER_MSG.REGISTER_SUCCESS);
        navigate('/home');
      }
    } catch (err) {
      message.error({
        content: err.response.data.message || TOASTER_MSG.REGISTER_FAILED,
        duration: 2,
      });
    } finally {
      onSetLoading(false);
    }
  };

  return (
    <div
      className={cx(
        styles.formContainer,
        styles.signUpContainer,
      )}
    >
      <form>
        {/* social links */}
        <h1>Create Account</h1>
        <div className={styles.socialContainer}>
          <GoogleAuth />
        </div>
        <span>or use your email for registration</span>
        <input
          type="text"
          name="name"
          value={name}
          onChange={handleChange}
          placeholder="Name"
        />
        <input
          type="email"
          name="email"
          value={email}
          onChange={handleChange}
          placeholder="Email"
        />
        <input
          type="password"
          name="password"
          value={password}
          onChange={handleChange}
          placeholder="Password"
        />
        <input
          type="tel"
          name="phoneNumber"
          value={phoneNumber}
          onChange={handleChange}
          placeholder="Phone Number"
        />
        <Button
          className={styles.registerLoginBtn}
          type="button"
          onClick={handleOnSubmit}
          loading={isLoading}
        >
          Sign Up
        </Button>
      </form>
    </div>
  );
}

SignUp.propTypes = {
  isLoading: PropTypes.bool,
  name: PropTypes.string,
  email: PropTypes.string,
  userData: PropTypes.object,
  password: PropTypes.string,
  phoneNumber: PropTypes.string,
  onUpdateFields: PropTypes.func,
  onSetLoading: PropTypes.func,
  onUpdateUserData: PropTypes.func,
};

SignUp.defaultProps = {
  isLoading: false,
  name: EMPTY_STRING,
  email: EMPTY_STRING,
  userData: EMPTY_OBJECT,
  password: EMPTY_STRING,
  phoneNumber: EMPTY_STRING,
  onUpdateFields: EMPTY_FUNCTION,
  onSetLoading: EMPTY_FUNCTION,
  onUpdateUserData: EMPTY_FUNCTION,
};

const mapStateToProps = ({ loginSignupReducer }) => ({
  name: _get(loginSignupReducer, 'name'),
  email: _get(loginSignupReducer, 'email'),
  userData: _get(loginSignupReducer, 'userData'),
  password: _get(loginSignupReducer, 'password'),
  isLoading: _get(loginSignupReducer, 'isLoading'),
  phoneNumber: _get(loginSignupReducer, 'phoneNumber'),
});

const mapDispatchToProps = dispatch => ({
  onSetLoading: payload => dispatch(setLoading(payload)),
  onUpdateFields: payload => dispatch(updateFields(payload)),
  onUpdateUserData: payload => dispatch(updateUserData(payload)),
});

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(SignUp);
