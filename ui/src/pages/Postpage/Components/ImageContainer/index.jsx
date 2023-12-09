/* eslint-disable react/prop-types */
import React from 'react';
/* styles */
import styles from './Image.module.scss';
// eslint-disable-next-line no-unused-vars
function ImageComponent({ base64 }) {
  return (
    <img
      className={styles.postImage}
      src={base64}
      alt="desc"
      height="100%"
      width="100%"
    />
  );
}

export default ImageComponent;
