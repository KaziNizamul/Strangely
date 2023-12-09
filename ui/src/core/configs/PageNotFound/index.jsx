/* external imports */
import React from 'react';
import { Link } from 'react-router-dom';
/* styles */
import styles from './PageNotFound.module.scss';
/* internal components */
import withNavbar from '../../../resources/shared/hoc/navbar';

function PageNotFound() {
  return (
    <div className={styles.notFoundContainer}>
      <div className={styles.notFoundContent}>
        <h1 className={styles.animatedFigure}>404</h1>
        <p>Page Not Found</p>
        <Link to="/">Go to Main Page</Link>
      </div>
    </div>
  );
}

export default withNavbar(PageNotFound);
