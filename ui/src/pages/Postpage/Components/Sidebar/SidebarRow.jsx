import React from 'react';
import PropTypes from 'prop-types';
import Avatar from '@mui/material/Avatar';
import './SidebarRow.css';

function SidebarRow({
  src,
  Icon,
  title,
  onClick,
}) {
  return (
    <div
      tabIndex="0"
      role="button"
      className="sidebarRow"
      onClick={onClick}
    >
      {src && <Avatar src={src} />}
      {Icon && <Icon />}
      <h4>{title}</h4>
    </div>
  );
}

SidebarRow.propTypes = {
  src: PropTypes.string.isRequired,
  Icon: PropTypes.elementType.isRequired,
  title: PropTypes.string.isRequired,
  onClick: PropTypes.func.isRequired,
};

export default SidebarRow;
