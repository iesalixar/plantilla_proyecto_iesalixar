
const CommentIcon = (props) => {
    return (
        <svg xmlns="http://www.w3.org/2000/svg" width="2.2em" height="2.2em" viewBox="0 0 26 22" {...props}>
            <path fill="none" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M12 21a9 9 0 1 0-9-9c0 1.488.36 2.891 1 4.127L3 21l4.873-1c1.236.64 2.64 1 4.127 1" />
        </svg>
    );
};

// Componente LikeIcon
const LikeIcon = (props) => {
    return (
        <svg xmlns="http://www.w3.org/3000/svg" width="2em" height="2em" viewBox="0 0 48 40" {...props}>
            <path fill="none" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="4" d="M15 8C8.925 8 4 12.925 4 19c0 11 13 21 20 23.326C31 40 44 30 44 19c0-6.075-4.925-11-11-11c-3.72 0-7.01 1.847-9 4.674A10.987 10.987 0 0 0 15 8" />
        </svg>
    );
};

export { CommentIcon, LikeIcon };