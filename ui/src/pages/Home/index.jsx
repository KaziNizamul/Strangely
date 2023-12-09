import React, { useEffect, useState, memo } from 'react';
/* external imports */
import { Layout, message } from 'antd';
import {
  Button,
  Card,
  Typography,
  Grid,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
} from '@mui/material';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { v4 as randomUUID } from 'uuid';
/* hoc */
import withNavbar from '../../resources/shared/hoc/navbar';
import withSidebar from '../../resources/shared/hoc/sidebar';
/* internal component */
import GeoFetch from '../../components/molecules/geoFetch';
/* constant */
import {
  TOASTER_MSG,
} from './constants/Home.constant';
import {
  EMPTY_ARRAY,
  EMPTY_FUNCTION,
  EMPTY_STRING,
} from '../../resources/shared/global.constant';
/* styles */
import styles from './Home.module.scss';
/* service */
import {
  getAreaId,
  postNewCategory,
  getAllCategory,
} from './service/Home.service';
/* actions */
import { updateAreaId } from '../../components/organisms/LoginSignup/data/LoginSingup.actions';

const { Content } = Layout;

function Home({ onUpdateAreaId = EMPTY_FUNCTION }) {
  const [open, setOpen] = React.useState(false);
  const [categoryName, setCategoryName] = useState('');
  const [categories, setCategories] = useState([]);
  useEffect(() => {
    fetchAllCategory();
  }, []);

  const fetchAllCategory = () => {
    getAllCategory()
      .then((response) => {
        const modifiedData = (response?.data || []).map(category => ({
          categoryId: category?.postCategoryId,
          title: category?.postCategoryName,
          description: `Join us for a session of ${category.postCategoryName}!`,
          imageSrc: `https://source.unsplash.com/800x600/?${category.postCategoryName.toLowerCase()}`,
        }));
        setCategories(modifiedData);
      })
      .catch();
  };

  const handleLocationUpdate = (location) => {
    const payload = {
      latitude: location.latitude,
      longitude: location.longitude,
    };
    getAreaId(payload)
      .then((response) => {
        onUpdateAreaId(response?.data?.area_id || 1);
      })
      .catch(() => {
        message.error(TOASTER_MSG.FAILED_TO_GET_AREA_ID);
      });
  };

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleInputChange = (event) => {
    setCategoryName(event.target.value);
  };

  const handleAddCategory = (category) => {
    const payload = {
      postCategoryName: category,
    };

    postNewCategory(payload)
      .then(() => {
        message.success(
          `${TOASTER_MSG.CREATE_NEW_CATEGORY_SUCCESS}${category}`,
        );
        message.success(TOASTER_MSG.CREATE_NEW_CATEGORY_SUCCESS);
        setOpen(false);
        fetchAllCategory();
      })
      .catch(() => {
        message.error(TOASTER_MSG.POST_NEW_CATEGORY_FAILED);
      });
  };

  return (
    <>
      <GeoFetch onLocationUpdate={handleLocationUpdate} />
      <div className={styles.homeContainer}>
        <Content style={{ padding: '0 24px', minHeight: 280 }}>
          <Typography
            variant="h4"
            component="h2"
            style={{ padding: '16px', textAlign: 'center' }}
          >
            <span className={styles.welcomeText}> Welcome to </span>
            <span className={styles.strangelyText}> Strangely! </span>
          </Typography>
          <Typography
            variant="h6"
            style={{ padding: '0 16px 16px', textAlign: 'center' }}
          >
            <span className={styles.subText}>
              Please select a category from below to explore more events 🎊
              happening near you 📍.
            </span>
            <Button
              variant="contained"
              color="primary"
              onClick={handleClickOpen}
            >
              Add New Category
            </Button>
            <Dialog open={open} onClose={handleClose}>
              <DialogTitle>Add New Category</DialogTitle>
              <DialogContent>
                <DialogContentText sx={{ fontSize: '1.5rem' }}>
                  Please enter the name of the new category.
                </DialogContentText>
                <TextField
                  autoFocus
                  margin="dense"
                  id="name"
                  label="Category Name"
                  type="text"
                  fullWidth
                  value={categoryName}
                  onChange={handleInputChange}
                />
              </DialogContent>
              <DialogActions>
                <Button onClick={handleClose} style={{ fontSize: '1rem' }}>
                  Cancel
                </Button>
                <Button
                  style={{ fontSize: '1rem' }}
                  onClick={() => handleAddCategory(categoryName)}
                >
                  Add
                </Button>
              </DialogActions>
            </Dialog>
          </Typography>
          <Grid container justifyContent="center" spacing={2}>
            {(categories || EMPTY_ARRAY).map(activity => (
              <Grid key={randomUUID()} item xs={12} sm={6} md={4} lg={3}>
                <Link
                  to={`/feed/${
                    activity?.title?.toLowerCase() || EMPTY_STRING
                  }/${activity?.categoryId || EMPTY_STRING}`}
                  className={styles.cardItem}
                >
                  <Card sx={{ maxWidth: 345 }}>
                    <img
                      alt={activity?.title || EMPTY_STRING}
                      src={activity?.imageSrc || EMPTY_STRING}
                      style={{ width: '28rem', height: '18.7rem' }}
                    />
                    <Typography
                      gutterBottom
                      variant="h5"
                      component="div"
                      style={{ padding: '16px' }}
                    >
                      {activity?.title || EMPTY_STRING}
                    </Typography>
                    <Typography
                      variant="body2"
                      color="text.secondary"
                      style={{ padding: '0 16px 16px' }}
                    >
                      {activity?.description || EMPTY_STRING}
                    </Typography>
                  </Card>
                </Link>
              </Grid>
            ))}
          </Grid>
        </Content>
      </div>
    </>
  );
}

Home.propTypes = {
  onUpdateAreaId: PropTypes.func,
};

Home.defaultProps = {
  onUpdateAreaId: EMPTY_FUNCTION,
};

const mapDispatchToProps = dispatch => ({
  onUpdateAreaId: payload => dispatch(updateAreaId(payload)),
});

export default connect(
  null,
  mapDispatchToProps,
)(withSidebar(withNavbar(memo(Home))));
