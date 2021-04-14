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
		const STYLING = {
			width: this.props.percentage + "%"
		}



		return (
			<div className="progress">
				<div className={"progress-bar " + this.calculateBackground(this.props.percentage)} style={STYLING} role="progressbar" aria-valuenow={this.props.percentage} aria-valuemin="0" aria-valuemax="100">{this.props.percentage}%</div>
			</div>
		)
	}
}

export default ProgressBar;