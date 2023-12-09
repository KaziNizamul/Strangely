import React from 'react';
/* external imports */
import { Spin } from 'antd';
/* styles */
import styles from './Spinner.module.scss';

function Spinner() {
  return (
    <div
      className={styles.spinnerContainer}
    >
      <div>
        Lets find out events in your locality ! 📍
      </div>
      <div>
        <Spin />
      </div>
    </div>
  );
}

export default Spinner;
