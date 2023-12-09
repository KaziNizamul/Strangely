/* external imports */
import React from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import cx from 'classnames';
import { Button } from 'antd';
/* utils */
import _get from 'lodash/get';
/* constants */
import {
  EMPTY_FUNCTION,
  EMPTY_OBJECT,
  EMPTY_STRING,
} from '../../../../../resources/shared/global.constant';
/* actions */
import { updateCheckinType } from '../../data/LoginSingup.actions';
/* styles */
import styles from '../LoginSignup.module.scss';

function OverlayContainer({
  onUpdateCheckinType = EMPTY_FUNCTION,
}) {
  const handleOnClickOverlay = (evt) => {
    const {
      currentTarget: {
        id = EMPTY_STRING,
      } = EMPTY_OBJECT,
    } = evt || EMPTY_OBJECT;
    onUpdateCheckinType({ checkinType: id });
  };

  return (
    <div className={styles.overlayContainer}>
      <div className={styles.overlay}>
        {/* login */}
        <div
          className={cx(
            styles.overlayPanel,
            styles.overlayLeft,
          )}
        >
          <h1>Welcome Back!</h1>
          <p>
            To keep connected with us please login with your personal info
          </p>
          <Button
            type="button"
            className={cx(
              styles.registerLoginBtn,
              styles.ghost,
            )}
            id="signIn"
            onClick={handleOnClickOverlay}
          >
            Sign In
          </Button>
        </div>
        {/* logout */}
        <div
          className={cx(
            styles.overlayPanel,
            styles.overlayRight,
          )}
        >
          <h1>Welcome to Strangely !</h1>
          <p>Share your story and connect with others on a unique journey</p>
          <Button
            type="button"
            className={cx(
              styles.registerLoginBtn,
              styles.ghost,
            )}
            id="signUp"
            onClick={handleOnClickOverlay}
          >
            Sign Up
          </Button>
        </div>
      </div>
    </div>
  );
}

OverlayContainer.propTypes = {
  onUpdateCheckinType: PropTypes.func,
};

OverlayContainer.defaultProps = {
  onUpdateCheckinType: EMPTY_FUNCTION,
};

const mapStateToProps = ({ loginSignupReducer }) => ({
  checkinType: _get(loginSignupReducer, 'checkinType'),
});

const mapDispatchToProps = dispatch => ({
  onUpdateCheckinType: payload => dispatch(updateCheckinType(payload)),
});

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(OverlayContainer);
