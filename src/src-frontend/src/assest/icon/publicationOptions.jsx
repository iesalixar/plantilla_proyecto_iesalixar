import * as React from "react"
const TrashIcon = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={24}
        height={24}
        viewBox="0 0 24 24"
        {...props}
    >
        <path
            fill="#c81414"
            d="M7 21q-.825 0-1.412-.587T5 19V6H4V4h5V3h6v1h5v2h-1v13q0 .825-.587 1.413T17 21zm2-4h2V8H9zm4 0h2V8h-2z"
        />
    </svg>
);
const EditIcon = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={24}
        height={24}
        viewBox="0 0 24 24"
        {...props}
    >
        <path
            fill="#14c832"
            d="M5 19h1.425L16.2 9.225 14.775 7.8 5 17.575zm-2 2v-4.25L17.625 2.175 21.8 6.45 7.25 21zM19 6.4 17.6 5zm-3.525 2.125-.7-.725L16.2 9.225z"
        />
    </svg>
);
export { TrashIcon, EditIcon };
