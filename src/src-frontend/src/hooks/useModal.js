import { useState } from 'react';

const useModal = () => {
    const [modals, setModals] = useState({});

    const openModal = (modalId) => {
        setModals((prevModals) => ({ ...prevModals, [modalId]: true }));
    };

    const closeModal = (modalId) => {
        setModals((prevModals) => ({ ...prevModals, [modalId]: false }));
    };

    const isModalOpen = (modalId) => !!modals[modalId];

    return {
        openModal,
        closeModal,
        isModalOpen,
    };
};

export default useModal;
