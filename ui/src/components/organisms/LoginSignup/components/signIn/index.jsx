/* eslint-disable no-restricted-syntax */
/* eslint-disable guard-for-in */

/* external imports */
import React from 'react';
import { connect } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import PropTypes from 'prop-types';
import cx from 'classnames';
import { Button, message } from 'antd';
/* utils */
import _get from 'lodash/get';
import Utility from '../../../../../utils/Utility';
/* actions */
import {
  setLoading,
  updateFields,
  updateUserData,
} from '../../data/LoginSingup.actions';
/* internal components */
import GoogleAuth from '../googleAuth';
/* constants */
import {
  EMPTY_OBJECT,
  EMPTY_STRING,
  GLOBAL_CONST,
  EMPTY_FUNCTION,
  EMPTY_ARRAY,
} from '../../../../../resources/shared/global.constant';
import { TOASTER_MSG } from '../../constants/LoginSignup.constant';
/* services */
import { userLogin } from '../../service/LoginSignup.service';
/* styles */
import styles from '../LoginSignup.module.scss';

function SignIn({
  isLoading = false,
  username = EMPTY_STRING,
  password = EMPTY_STRING,
  // userData = EMPTY_OBJECT,
  onUpdateFields = EMPTY_FUNCTION,
  onSetLoading = EMPTY_FUNCTION,
  onUpdateUserData = EMPTY_FUNCTION,
}) {
  const navigate = useNavigate();

  // useEffect(() => {
  //   if (Utility.isObjectDefined(userData)
  //   && ) {
  //     navigate('/home');
  //   }
  // }, [navigate]);

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
    const payload = {
      username,
      password,
    };
    onSetLoading(true);

    try {
      const response = await userLogin(payload);
      if (Utility.isObjectDefined(response)) {
        const { data = EMPTY_ARRAY } = response || EMPTY_OBJECT;
        onUpdateUserData(data);
        navigate('/home');
      }
    } catch (err) {
      message.error({
        content: err?.response?.data?.message || TOASTER_MSG.LOGIN_FAILURE,
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
        styles.signInContainer,
      )}
    >
      <form>
        <h1>Sign in</h1>
        {/* social links */}
        <div className={styles.socialContainer}>
          <GoogleAuth />
        </div>
        <span>or use your account</span>
        {/* email */}
        <input
          type="text"
          name="name"
          value={username}
          onChange={handleChange}
          placeholder="User Name"
        />
        {/* password */}
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={password}
          onChange={handleChange}
        />
        {/* forgot password */}
        <div className={styles.forgotPassword}>
          <Link to="/resetpasswordSS">Forgot your password?</Link>
        </div>
        {/* signin btn */}
        <Button
          className={styles.registerLoginBtn}
          type="submit"
          onClick={handleOnSubmit}
          loading={isLoading}
        >
          Sign In
        </Button>
      </form>
    </div>
  );
}

SignIn.propTypes = {
  isLoading: PropTypes.bool,
  username: PropTypes.string,
  password: PropTypes.string,
  // userData: PropTypes.object,
  onUpdateFields: PropTypes.func,
  onSetLoading: PropTypes.func,
  onUpdateUserData: PropTypes.func,
};

SignIn.defaultProps = {
  isLoading: false,
  username: EMPTY_STRING,
  password: EMPTY_STRING,
  // userData: EMPTY_OBJECT,
  onUpdateFields: EMPTY_FUNCTION,
  onSetLoading: EMPTY_FUNCTION,
  onUpdateUserData: EMPTY_FUNCTION,
};

const mapStateToProps = ({ loginSignupReducer }) => ({
  userData: _get(loginSignupReducer, 'userData'),
  isLoading: _get(loginSignupReducer, 'isLoading'),
  username: _get(loginSignupReducer, 'name'),
  password: _get(loginSignupReducer, 'password'),
});

const mapDispatchToProps = dispatch => ({
  onSetLoading: payload => dispatch(setLoading(payload)),
  onUpdateFields: payload => dispatch(updateFields(payload)),
  onUpdateUserData: payload => dispatch(updateUserData(payload)),
});

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(SignIn);
