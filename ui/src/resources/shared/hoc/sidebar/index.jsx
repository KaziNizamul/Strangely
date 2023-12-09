/* eslint-disable func-names */
import React from 'react';
import Sidebar from '../../../../pages/Postpage/Components/Sidebar';

const withSidebar = WrappedComponent => function (props) {
  return (
    <div style={{ display: 'flex' }}>
      <Sidebar />
      <WrappedComponent {...props} />
    </div>
  );
};

export default withSidebar;
