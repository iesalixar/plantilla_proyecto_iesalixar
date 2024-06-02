import React from 'react';
import './style.scss';

const ProfilePictureCircle = ({ source, size = 50 }) => {
    return (
        <div className="profile-picture-circle" style={{ width: size, height: size, borderRadius: '50%' }}>
            <img src={source} alt="Profile" style={{ width: size, height: size, borderRadius: '50%' }} />
        </div>
    );
};

export default ProfilePictureCircle;