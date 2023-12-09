/* external imports */
import React from 'react';
import {
  useSelector,
  useDispatch,
} from 'react-redux';
import {
  NavLink,
  useLocation,
  useNavigate,
} from 'react-router-dom';
import {
  HomeOutlined,
  TeamOutlined,
} from '@ant-design/icons';
import {
  message,
  // Drawer,
} from 'antd';
import cx from 'classnames';
/* resources */
import logoutIcon from '../../../resources/assets/logout.svg';
/* styles */
import styles from './Navbar.module.scss';
/* constants */
import { TOASTER_MSG } from '../../organisms/LoginSignup/constants/LoginSignup.constant';
/* service */
import { logoutUser } from '../../organisms/LoginSignup/service/LoginSignup.service';
/* actions */
import { resetAllData } from '../../organisms/LoginSignup/data/LoginSingup.actions';

function Navbar() {
  const userData = useSelector(state => state.loginSignupReducer.userData);
  const dispatch = useDispatch();
  const location = useLocation();
  const navigate = useNavigate();

  const handleLogout = () => {
    const { userName = '', token = '' } = userData;
    logoutUser({ userName, token })
      .then(() => {
        dispatch(resetAllData());
        message.success(TOASTER_MSG.LOGGED_OUT_SUCCESSFULLY);
        navigate('/');
      })
      .catch((err) => {
        message.error(err.message || TOASTER_MSG.LOGGED_OUT_FAILED);
      });
  };

  return (
    <nav className={styles.navbar}>
      <ul className={styles.navList}>
        {/* LEFT PANEL: home */}
        <li className={styles.navItem}>
          <NavLink
            title="Home"
            to="/home"
            className={styles.homeText}
          >
            Home
          </NavLink>
        </li>
        {/* MID PANEL: home */}
        { userData && location.pathname !== '/' && (
          <div
            className={styles.midPanel}
          >
            {/* home */}
            <li className={styles.navItem}>
              <NavLink
                title="Home"
                to="/home"
                activeClassName={styles.activeLink}
                className={
                  cx(
                    styles.navLink,
                    styles.homeBtn,
                  )
                }
              >
                <HomeOutlined />
              </NavLink>
            </li>
            {/* connection */}
            <li className={styles.navItem}>
              <NavLink
                title="Connections"
                activeClassName={styles.activeLink}
                to="/chat"
                className={
                  cx(
                    styles.navLink,
                    styles.chatBtn,
                  )
                }
              >
                <TeamOutlined />
              </NavLink>
            </li>
          </div>
        )}
        {/* home */}
        {/* RIGHT PANEL: logout */}
        { userData && location.pathname !== '/' && (
          <div className={styles.rightSideNavItem}>
            {/* logout */}
            <li className={styles.navItem}>
              <div
                role="button"
                tabIndex="0"
                className={styles.logoutButton}
                onClick={handleLogout}
              >
                <img
                  title="logout"
                  src={logoutIcon}
                  alt="Logout"
                />
              </div>
            </li>
          </div>
        )}
      </ul>
    </nav>
  );
}

export default Navbar;
