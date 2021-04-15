import React from 'react';

class ProgressBar extends React.Component {

	calculateBackground (percentage) {
		if (percentage > 75)
			return "bg-danger";
		else if (percentage > 50)
			return "bg-warning";
		else
			return "bg-success";
	}

	render() {
		const PROGRESS_BAR_WIDTH = { width: this.props.percentage + "%" };
		const SMALL_TEXT = { color:'#000000' };
		


		return (
			<div className="progress position-relative">
				<div className={"progress-bar " + this.calculateBackground(this.props.percentage)} style={PROGRESS_BAR_WIDTH} role="progressbar" aria-valuenow={this.props.percentage} aria-valuemin="0" aria-valuemax="100"></div>
				<small style={SMALL_TEXT} className="justify-content-center d-flex position-absolute w-100">{this.props.percentage}%</small>
			</div>
		)
	}
}

export default ProgressBar;