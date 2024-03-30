import styles from './LoadingModal.module.css';

const LoadingModal = function ({text}) {
  return (
    <div className={styles.loading_container}>
      <div className={styles.loading_modal_container}>
        <img className={styles.loading_img} src="/imgs/ch1_bol_normal_run.gif" alt="loading_img" />
        <div className={styles.loading_txt} >{text}</div>
      </div>
    </div>
  )
}

export default LoadingModal;