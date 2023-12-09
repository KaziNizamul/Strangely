/* external components */
import React from 'react';
import { connect } from 'react-redux';
import cx from 'classnames';
/* external utils */
import _get from 'lodash/get';
import PropTypes from 'prop-types';
/* internal components */
import SignIn from './signIn';
import SignUp from './signUp';
import OverlayContainer from './overlay';
/* assets */
// import logo from '../../../../resources/assets/logo.png';
/* constants */
import {
  EMPTY_STRING,
} from '../../../../resources/shared/global.constant';
import { STRING_CONSTANTS } from '../constants/LoginSignup.constant';
/* styles */
import styles from './LoginSignup.module.scss';

function LoginSignup({
  checkinType = EMPTY_STRING,
}) {
  return (
    <div className={styles.LoginSignupContainer}>
      {/* <img src={logo} alt="Strangely Logo" className={styles.logoImage} /> */}
      <div
        className={checkinType === STRING_CONSTANTS.SIGN_UP
          ? cx(styles.innerContainer, styles.rightActivePanel)
          : cx(styles.innerContainer)}
        id="container"
      >
        <SignIn />
        <SignUp />
        <OverlayContainer />
      </div>
    </div>
  );
}

LoginSignup.propTypes = {
  checkinType: PropTypes.string,
};

LoginSignup.defaultProps = {
  checkinType: EMPTY_STRING,
};

const mapStateToProps = ({ loginSignupReducer }) => ({
  checkinType: _get(loginSignupReducer, 'checkinType'),
});

const mapDispatchToProps = (/* dispatch */) => ({
});

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(LoginSignup);
