/* external imports */
import React, { useState } from 'react';
import PeopleOutlineIcon from '@mui/icons-material/PeopleOutline';
import CategoryIcon from '@mui/icons-material/Category';
import { Drawer } from 'antd';
import { useNavigate, useLocation } from 'react-router-dom';
import SidebarRow from './SidebarRow';
/* internal component */
import UserSetting from '../../../../components/molecules/UserSetting';
/* styles */
import styles from './Sidebar.module.scss';

function Sidebar() {
  const [isDrawerVisible, setIsDrawerVisible] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <div className={styles.sidebarContainer}>
      <SidebarRow
        title="Profile"
        src="https://images.unsplash.com/photo-1529665253569-6d01c0eaf7b6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"
        onClick={() => setIsDrawerVisible(true)}
      />

      <Drawer
        title="User Profile"
        placement="right"
        closable
        onClose={() => setIsDrawerVisible(false)}
        open={isDrawerVisible}
      >
        <UserSetting />
      </Drawer>

      <SidebarRow
        title="Connections"
        Icon={PeopleOutlineIcon}
        onClick={() => navigate('/chat')}
      />
      {location.pathname !== '/home' && (
        <SidebarRow
          title="Categories"
          Icon={CategoryIcon}
          onClick={() => navigate('/home')}
        />
      )}
    </div>
  );
}

export default Sidebar;
