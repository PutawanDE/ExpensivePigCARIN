import Rolling from '../../elements/loading/Rolling-200-px.svg';

function Spinner() {
  return (
    <div className="absolute flex h-screen w-screen bg-neutral-900/50 z-50">
      <div className="m-auto">
        <img src={Rolling} alt="Loading" />
      </div>
    </div>
  );
}

export default Spinner;
