// Input.js
import React from 'react';
import PropTypes from 'prop-types';
import {
  EMPTY_FUNCTION,
  EMPTY_STRING,
} from '../../../resources/shared/global.constant';

function Input({
  label,
  type,
  name,
  value,
  onChange,
  onBlur,
  ...rest
}) {
  return (
    <div className="input-container">
      <label htmlFor={name}>{label}</label>
      <input
        id={name}
        type={type}
        name={name}
        value={value}
        onChange={onChange}
        onBlur={onBlur}
        {...rest}
      />
    </div>
  );
}

Input.propTypes = {
  label: PropTypes.string,
  type: PropTypes.any.isRequired,
  name: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  onBlur: PropTypes.func,
};

Input.defaultProps = {
  label: EMPTY_STRING,
  onBlur: EMPTY_FUNCTION,
};

export default Input;
