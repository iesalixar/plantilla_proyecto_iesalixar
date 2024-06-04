import { useState } from 'react';

const useModal = () => {
    const [modalIsOpen, setModalOpen] = useState(false);

    const openModal = () => setModalOpen(true);
    const closeModal = () => setModalOpen(false);

    return {
        modalIsOpen,
        openModal,
        closeModal,
    };
};

export default useModal;
