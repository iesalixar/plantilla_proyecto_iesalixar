import React, { useContext, useState } from 'react';

import { ThemeContext } from '../../../../../contexts/ThemeProvider';
import { useAuthContext } from '../../../../../contexts/AuthProvider';

import { deleteActivity } from '../../../../../service/activityService';

import { DotsIconClear } from '../icon/activityIcon-clear';
import { DotsIconDark } from '../icon/activityIcon-dark';
import { TrashIcon, EditIcon } from '../../../../../assest/icon/publicationOptions';

import './style.scss';


const PublicationMenuButton = ({ activityId }) => {
    const { isDark, theme } = useContext(ThemeContext);
    const { userData } = useAuthContext();
    const token = userData.token

    const [isMenuOpen, setIsMenuOpen] = useState(false);
    const handleMenuToggle = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    const handleDeleteActivity = async () => {
        try {
            await deleteActivity(activityId, token);
            window.location.reload();

        } catch (error) {
            console.error('Error deleting activity:', error.message);
        }
    };

    const dotsIcon = isDark ? <DotsIconDark /> : <DotsIconClear />;

    return (
        <div className="publication-menu-button">
            <button onClick={handleMenuToggle}>
                {dotsIcon}
            </button>
            {isMenuOpen && (
                <div className="dropdown-menu" style={{ backgroundColor: theme.colorBackground }}>
                    <div className="dropdown-item" style={{ borderColor: theme.gray6 }}>
                        <EditIcon />
                        <span style={{ color: theme.gray11 }}>Edit</span>
                    </div>
                    <div className="dropdown-item" style={{ borderColor: theme.gray6 }}>
                        <TrashIcon />
                        <span style={{ color: theme.gray11 }}>Delete</span>
                    </div>
                </div>
            )}
        </div>
    );
};

export default PublicationMenuButton;