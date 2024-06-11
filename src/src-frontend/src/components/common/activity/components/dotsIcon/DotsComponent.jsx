import React, { useContext, useState } from 'react';

import { ThemeContext } from '../../../../../contexts/ThemeProvider';
import { useAuthContext } from '../../../../../contexts/AuthProvider';
import { useModalContext } from '../../../../../contexts/ModalProvider';
import { deleteActivity } from '../../../../../service/activityService';

import { DotsIconClear } from '../icon/activityIcon-clear';
import { DotsIconDark } from '../icon/activityIcon-dark';
import { TrashIcon, EditIcon } from '../../../../../assest/icon/publicationOptions';
import PatchActivityForm from '../patchActivity/patchActivityCard';

import './style.scss';



const PublicationMenuButton = ({ activity }) => {
    const { isDark, theme } = useContext(ThemeContext);
    const { userData } = useAuthContext();
    const { openModal, closeModal } = useModalContext();
    const handleOpenEditModal = () => openModal('editModal');
    const handleCloseEditModal = () => closeModal('editModal');
    const token = userData?.token;

    const [isMenuOpen, setIsMenuOpen] = useState(false);
    const [isEditing, setIsEditing] = useState(false);

    const handleMenuToggle = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    const handleDeleteActivity = async () => {
        try {
            await deleteActivity(activity.id, token);
            window.location.reload();
        } catch (error) {
            console.error('Error deleting activity:', error.message);
        }
    };

    const handleEditActivity = () => {
        setIsEditing(true);
        handleOpenEditModal();
    };

    const dotsIcon = isDark ? <DotsIconDark /> : <DotsIconClear />;

    return (
        <div className="publication-menu-button">
            <button onClick={handleMenuToggle}>
                {dotsIcon}
            </button>
            {isMenuOpen && (
                <div className="dropdown-menu" style={{ backgroundColor: theme.colorBackground }}>
                    <div className="dropdown-item" style={{ borderColor: theme.gray6 }} onClick={handleEditActivity}>
                        <EditIcon />
                        <span style={{ color: theme.gray11 }}>Edit</span>
                    </div>
                    <div className="dropdown-item" style={{ borderColor: theme.gray6 }} onClick={handleDeleteActivity}>
                        <TrashIcon />
                        <span style={{ color: theme.gray11 }}>Delete</span>
                    </div>
                </div>
            )}
            {isEditing && (
                <PatchActivityForm
                    activity={activity}
                    closeModal={() => {
                        setIsEditing(false);
                        handleCloseEditModal();
                    }}
                />
            )}
        </div>
    );
};

export default PublicationMenuButton;